package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.dto.account.NewOrUpdateAccountDto;

public interface AccountsService {

    AccountDto getAccountById(Long id);

    AccountDto addAccount(NewOrUpdateAccountDto account);

    AccountDto updateAccount(Long id, NewOrUpdateAccountDto updatedAccount);

    void deleteAccount(Long id);

    AccountDto confirmAccount(Long id);

    void banAccount(Long id);

}
