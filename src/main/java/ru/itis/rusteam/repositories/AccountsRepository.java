package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.account.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    Page<Account> findAllByStateOrderById(PageRequest pageRequest, Account.State state);
}
