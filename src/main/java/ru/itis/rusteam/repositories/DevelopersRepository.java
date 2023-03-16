package ru.itis.rusteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.Developer;

public interface DevelopersRepository extends JpaRepository<Developer, Long> {

    Developer findByAccount(Account state);
}
