package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.User;
import ru.itis.rusteam.repositories.UsersRepository;
import ru.itis.rusteam.services.UsersService;

import static ru.itis.rusteam.dto.account.user.UserDto.from;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;


    @Override
    public UserDto addUser(NewOrUpdateUserDto user) {
        User userToSave = User.builder()
                .account(getAccount(user))
                .name(user.getName())
                .surname(user.getSurname())
                .gender(user.getGender())
                .birthdayDate(user.getBirthdayDate())
                .build();

        //TODO - сделать проверку корректности данных
        usersRepository.save(userToSave);


        return from(userToSave);
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.from(getUserOrThrow(id));
    }

    @Override
    public UserDto updateUser(Long id, NewOrUpdateUserDto updatedUser) {
        User userForUpdate = getUserOrThrow(id);

        userForUpdate.setName(updatedUser.getName());
        userForUpdate.setSurname(updatedUser.getSurname());
        userForUpdate.setGender(updatedUser.getGender());
        userForUpdate.setBirthdayDate(updatedUser.getBirthdayDate());

        //TODO - сделать проверку корректности данных
        usersRepository.save(userForUpdate);

        return from(userForUpdate);
    }


    @Override
    public void deleteUser(Long id) {
        getUserOrThrow(id);

        //TODO - продумать удаление данных подтаблиц
        usersRepository.deleteById(id);
    }


    private User getUserOrThrow(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с идентификатором <" + id + "> не найден"));
    }

    private Account getAccount(NewOrUpdateUserDto user) {
        //TODO - тут, наверное, стоит сделать проверку, что аккаунт вообще существует
        return Account.builder().id(user.getAccountId()).build();
    }

}
