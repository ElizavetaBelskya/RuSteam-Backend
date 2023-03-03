package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.User;

/**
 * @author Elizaveta Belskaya
 */
public interface UsersRepository extends JpaRepository<User, Long> {
    Page<User> findAllByStateOrderById(PageRequest pageRequest, User.State state);
}
