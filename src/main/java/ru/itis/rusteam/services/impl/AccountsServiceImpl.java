package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.dto.account.NewOrUpdateAccountDto;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.repositories.AccountsRepository;
import ru.itis.rusteam.security.details.UserDetailsImpl;
import ru.itis.rusteam.security.exceptions.AlreadyExistsException;
import ru.itis.rusteam.services.AccountsService;

import java.security.Principal;

import static ru.itis.rusteam.dto.account.AccountDto.from;
import static ru.itis.rusteam.utils.ServicesUtils.getOrThrow;


@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long getAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String email = authentication.getName();
            return accountsRepository.findByEmailIgnoreCase(email).get().getId();
        }
        throw new AuthenticationCredentialsNotFoundException("Account is not authenticated");
    }

    @Override
    public AccountDto addAccount(NewOrUpdateAccountDto account) {
        if (isEmailUsed(account.getEmail())) {
            throw new AlreadyExistsException("Account with email <" + account.getEmail() + "> already exists");
        }
        if (isNicknameUsed(account.getNickname())) {
            throw new AlreadyExistsException("Account with nickname <" + account.getNickname() + "> already exists");
        }
        Account accountToSave = Account.builder()
                .email(account.getEmail())
                .nickname(account.getNickname())
                .passwordHash(passwordEncoder.encode(account.getPassword()))
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
        if (isEmailUsed(updatedAccount.getEmail())) {
            throw new AlreadyExistsException("Account with email <" + updatedAccount.getEmail() + "> already exists");
        }
        if (isNicknameUsed(updatedAccount.getNickname())) {
            throw new AlreadyExistsException("Account with nickname <" + updatedAccount.getNickname() + "> already exists");
        }
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
        return getOrThrow(id, accountsRepository, "Account");
    }

    private boolean isEmailUsed(String email) {
        return accountsRepository.findByEmailIgnoreCase(email).isPresent();
    }

    private boolean isNicknameUsed(String nickname) {
        return accountsRepository.findByNicknameIgnoreCase(nickname).isPresent();
    }


}
