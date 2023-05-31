package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.account.developer.DeveloperDto;
import ru.itis.rusteam.dto.account.developer.NewOrUpdateDeveloperDto;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.repositories.AccountsRepository;
import ru.itis.rusteam.repositories.DevelopersRepository;
import ru.itis.rusteam.services.DevelopersService;

import static ru.itis.rusteam.dto.account.developer.DeveloperDto.from;
import static ru.itis.rusteam.utils.ServicesUtils.getOrThrow;

@RequiredArgsConstructor
@Service
public class DevelopersServiceImpl implements DevelopersService {

    private final DevelopersRepository developersRepository;

    private final AccountsRepository accountsRepository;

    @Override
    public DeveloperDto addDeveloper(NewOrUpdateDeveloperDto developer) {
        Account account = getAccountOrThrow(developer.getAccountId());
        Developer developerToSave = Developer.builder()
                .account(getAccountOrThrow(developer.getAccountId()))
                .name(developer.getName())
                .description(developer.getDescription())
                .build();

        account.setRole(Account.Role.MODERATOR);
        accountsRepository.save(account);
        //TODO - сделать проверку корректности данных
        developersRepository.save(developerToSave);

        return from(developerToSave);
    }

    @Override
    public DeveloperDto getDeveloperById(Long id) {
        return from(developersRepository.findByAccount(getAccountOrThrow(id)));
    }

    @Override
    public DeveloperDto updateDeveloper(Long id, NewOrUpdateDeveloperDto updatedDeveloper) {
        Developer developerForUpdate = getDeveloperOrThrow(id);

        developerForUpdate.setName(updatedDeveloper.getName());
        developerForUpdate.setDescription(updatedDeveloper.getDescription());

        //TODO - сделать проверку корректности данных
        developersRepository.save(developerForUpdate);

        return from(developerForUpdate);
    }

    @Override
    public void deleteDeveloper(Long id) {
        getDeveloperOrThrow(id);

        //TODO - продумать удаление данных подтаблиц
        developersRepository.deleteById(id);
    }


    private Developer getDeveloperOrThrow(Long id) {
        return  getOrThrow(id, developersRepository, "Developer");
    }

    private Account getAccountOrThrow(Long id) {
        return getOrThrow(id, accountsRepository, "Account");
    }
}
