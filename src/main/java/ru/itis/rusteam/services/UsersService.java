package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.user.UserDto;
import ru.itis.rusteam.dto.user.UsersPage;

/**
 * @author Elizaveta Belskaya
 */
public interface UsersService {

    UserDto getUserById(Long id);

    UserDto addUser(NewOrUpdateUserDto user);

    void deleteUser(Long id);

    UsersPage getAllUsers(int page);

    UserDto updateUser(long id, NewOrUpdateUserDto updatedUser);


}
