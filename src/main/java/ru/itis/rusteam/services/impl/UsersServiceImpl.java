package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.User;
import ru.itis.rusteam.repositories.AccountsRepository;
import ru.itis.rusteam.repositories.UsersRepository;
import ru.itis.rusteam.services.UsersService;

import java.util.Optional;

import static ru.itis.rusteam.dto.account.user.UserDto.from;
import static ru.itis.rusteam.utils.ServicesUtils.getOrThrow;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;


    @Override
    public UserDto addUser(NewOrUpdateUserDto userDto) {
        Optional<Account> account = accountsRepository.findById(userDto.getAccountId());
        User user = usersRepository.findByAccount(account.get());
        User userToSave = User.builder()
                .id(user.getId())
                .account(getAccountOrThrow(userDto.getAccountId()))
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .gender(User.Gender.valueOf(userDto.getGender()))
                .birthdayDate(userDto.getBirthdayDate())
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
        userForUpdate.setGender(User.Gender.valueOf(updatedUser.getGender()));
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

    @Override
    public UserDto getUserByAccountId(Long accountId) {
        Optional<Account> account = accountsRepository.findById(accountId);
        User user = usersRepository.findByAccount(account.get());
        return UserDto.from(user);
    }


    private User getUserOrThrow(Long id) {
        return getOrThrow(id, usersRepository, "User");
    }

    private Account getAccountOrThrow(Long id) {
        return getOrThrow(id, accountsRepository, "Account");
    }



}
