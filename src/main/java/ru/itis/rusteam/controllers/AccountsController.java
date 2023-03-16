package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.AccountsApi;
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.dto.account.NewOrUpdateAccountDto;
import ru.itis.rusteam.services.AccountsService;


@RequiredArgsConstructor
@RestController
public class AccountsController implements AccountsApi {

    private final AccountsService accountsService;


    @Override
    public ResponseEntity<AccountDto> addAccount(NewOrUpdateAccountDto newAccount) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountsService.addAccount(newAccount));
    }

    @Override
    public ResponseEntity<AccountDto> getAccount(Long accountId) {
        return ResponseEntity
                .ok(accountsService.getAccountById(accountId));
    }

    @Override
    public ResponseEntity<AccountDto> updateAccount(Long accountId, NewOrUpdateAccountDto updatedAccount) {
        return ResponseEntity.accepted()
                .body(accountsService.updateAccount(accountId, updatedAccount));
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long accountId) {
        accountsService.deleteAccount(accountId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<AccountDto> confirmAccount(Long accountId) {
        return ResponseEntity.accepted()
                .body(accountsService.confirmAccount(accountId));
    }
}
