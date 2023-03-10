package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.deprecated.UserDepr;

/**
 * @author Elizaveta Belskaya
 */
public interface UsersRepository extends JpaRepository<UserDepr, Long> {
    Page<UserDepr> findAllByStateOrderById(PageRequest pageRequest, UserDepr.State state);
}
