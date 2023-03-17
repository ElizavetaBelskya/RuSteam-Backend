package ru.itis.rusteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.account.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
}
