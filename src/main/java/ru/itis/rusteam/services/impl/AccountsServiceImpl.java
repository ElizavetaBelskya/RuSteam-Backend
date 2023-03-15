package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.dto.account.NewOrUpdateAccountDto;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.repositories.AccountsRepository;
import ru.itis.rusteam.services.AccountsService;

import static ru.itis.rusteam.dto.account.AccountDto.from;


@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    public AccountDto addAccount(NewOrUpdateAccountDto account) {
        Account accountToSave = Account.builder()
                .email(account.getEmail())
                .nickname(account.getNickname())
                .passwordHash(account.getPassword())
                .state(Account.State.NOT_CONFIRMED)
                .role(Account.Role.USER)
                .build();

        //TODO - сделать проверку корректности данных
        accountsRepository.save(accountToSave);

        //TODO - временная заглушка подтверждения email
        confirmAccount(accountToSave.getId());
        accountToSave.setState(Account.State.CONFIRMED);

        return from(accountToSave);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        return from(getAccountOrThrow(id));
    }

    @Override
    public AccountDto updateAccount(Long id, NewOrUpdateAccountDto updatedAccount) {
        Account accountForUpdate = getAccountOrThrow(id);

        accountForUpdate.setEmail(updatedAccount.getEmail());
        accountForUpdate.setNickname(updatedAccount.getNickname());
        accountForUpdate.setPasswordHash(updatedAccount.getPassword());

        //TODO - сделать проверку корректности данных
        accountsRepository.save(accountForUpdate);

        return from(accountForUpdate);
    }

    @Override
    public void deleteAccount(Long id) {
        //TODO - продумать бл: из какого в какое состояния можно переводить
        changeState(getAccountOrThrow(id), Account.State.DELETED);
    }

    @Override
    public AccountDto confirmAccount(Long id) {
        //TODO - продумать бл: из какого в какое состояния можно переводить
        return from(changeState(getAccountOrThrow(id), Account.State.CONFIRMED));
    }

    @Override
    public void banAccount(Long id) {
        //TODO - продумать бл: из какого в какое состояния можно переводить
        changeState(getAccountOrThrow(id), Account.State.BANNED);
    }



    private Account changeState(Account account, Account.State newState) {
        account.setState(newState);
        accountsRepository.save(account);
        return account;
    }


    private Account getAccountOrThrow(Long id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Аккаунт с идентификатором <" + id + "> не найден"));
    }



}
