package ru.itis.rusteam.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.account.Token;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValue(String value);

}
