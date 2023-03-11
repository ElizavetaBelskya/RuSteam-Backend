package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rusteam.controllers.api.UserApi;
import ru.itis.rusteam.dto.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.UserDto;
import ru.itis.rusteam.dto.UsersPage;
import ru.itis.rusteam.services.UsersService;

/**
 * @author Elizaveta Belskaya
 */
@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UsersService usersService;
    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @Override
    public ResponseEntity<UserDto> addUser(NewOrUpdateUserDto newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.addUser(newUser));
    }

    @Override
    public ResponseEntity<UsersPage> getAllUsers(int page) {
        return ResponseEntity
                .ok(usersService.getAllUsers(page));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long lessonId, NewOrUpdateUserDto updatedUser) {
        return ResponseEntity.accepted().body(usersService.updateUser(lessonId, updatedUser));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }


}
