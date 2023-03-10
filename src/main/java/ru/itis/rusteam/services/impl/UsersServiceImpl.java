package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.user.UserDto;
import ru.itis.rusteam.dto.user.UsersPage;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.deprecated.UserDepr;
import ru.itis.rusteam.repositories.UsersRepository;
import ru.itis.rusteam.services.UsersService;


/**
 * @author Elizaveta Belskaya
 */
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
    @Value("${default.page-size}")
    private int defaultPageSize;


    private final UsersRepository usersRepository;

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.from(getUserOrThrow(id));
    }

    @Override
    public UserDto addUser(NewOrUpdateUserDto userDto) {
        UserDepr user = UserDepr.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .build();
        return UserDto.from(usersRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        UserDepr userForDelete = getUserOrThrow(id);
        userForDelete.setState(UserDepr.State.DELETED);
        usersRepository.save(userForDelete);
    }

    @Override
    public UsersPage getAllUsers(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<UserDepr> userPage = usersRepository.findAllByStateOrderById(pageRequest, UserDepr.State.ALIVE);

        return UsersPage.builder()
                .lessons(UserDto.from(userPage.getContent()))
                .totalPagesCount(userPage.getTotalPages())
                .build();

    }

    @Override
    public UserDto updateUser(long id, NewOrUpdateUserDto updatedUser) {
        UserDepr userForUpdate = getUserOrThrow(id);

        userForUpdate.setNickname(updatedUser.getNickname());
        usersRepository.save(userForUpdate);
        return UserDto.from(userForUpdate);

    }


    private UserDepr getUserOrThrow(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id <" + userId + "> not found"));
    }



}
