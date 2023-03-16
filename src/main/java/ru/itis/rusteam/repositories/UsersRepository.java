package ru.itis.rusteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.User;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByAccount(Account state);
}
