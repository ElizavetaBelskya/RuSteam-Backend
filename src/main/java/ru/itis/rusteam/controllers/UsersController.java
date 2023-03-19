package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.UsersApi;
import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;
import ru.itis.rusteam.services.UsersService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;

    @Override
    public ResponseEntity<UserDto> addUser(@Valid NewOrUpdateUserDto newUser) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersService.addUser(newUser));
    }
}
