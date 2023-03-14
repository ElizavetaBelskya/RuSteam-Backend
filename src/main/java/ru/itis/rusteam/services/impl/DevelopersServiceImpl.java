package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.account.developer.DeveloperDto;
import ru.itis.rusteam.dto.account.developer.NewOrUpdateDeveloperDto;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.repositories.DevelopersRepository;
import ru.itis.rusteam.services.DevelopersService;

import static ru.itis.rusteam.dto.account.developer.DeveloperDto.from;

@RequiredArgsConstructor
@Service
public class DevelopersServiceImpl implements DevelopersService {

    private final DevelopersRepository developersRepository;

    @Override
    public DeveloperDto addDeveloper(NewOrUpdateDeveloperDto developer) {
        Developer developerToSave = Developer.builder()
                .account(getAccount(developer))
                .name(developer.getName())
                .description(developer.getDescription())
                .build();

        //TODO - сделать проверку корректности данных
        developersRepository.save(developerToSave);

        return from(developerToSave);
    }

    @Override
    public DeveloperDto getDeveloperById(Long id) {
        return from(getDeveloperOrThrow(id));
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
        return developersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Разработчик с идентификатором <" + id + "> не найден"));
    }

    private Account getAccount(NewOrUpdateDeveloperDto developer) {
        //TODO - тут, наверное, стоит сделать проверку, что аккаунт вообще существует
        return Account.builder().id(developer.getAccountId()).build();
    }
}
