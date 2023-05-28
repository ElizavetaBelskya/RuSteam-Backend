package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;


public interface UsersService {

    UserDto getUserById(Long id);

    UserDto addUser(NewOrUpdateUserDto user);

    UserDto updateUser(Long id, NewOrUpdateUserDto updatedUser);

    void deleteUser(Long id);

    UserDto getUserByAccountId(Long accountId);


}
