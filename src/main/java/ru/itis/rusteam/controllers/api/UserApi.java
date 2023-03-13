package ru.itis.rusteam.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;

/**
 * @author Elizaveta Belskaya
 */
@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/users")
public interface UserApi {
    @Operation(summary = "Getting user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
                    })
    })
    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getUserById(@Parameter(description = "id of user") @RequestParam("id") long id);

    @Operation(summary = "Adding new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Data of added user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<UserDto> addUser(@RequestBody NewOrUpdateUserDto newUser);



    @Operation(summary = "Getting list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with users",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UsersPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<UsersPage> getAllUsers(@Parameter(description = "Number of page") @RequestParam("page") int page);

    @PutMapping("/{user-id}")
    ResponseEntity<UserDto> updateUser(
            @Parameter(description = "id of user", example = "1") @PathVariable("user-id") Long userId,
            @RequestBody NewOrUpdateUserDto updatedUser);


    @Operation(summary = "Changing state of user to DELETED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User is deleted"),
            @ApiResponse(responseCode = "404", description = "Message of error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    })
    @DeleteMapping("/{user-id}")
    ResponseEntity<?> deleteUser(
            @Parameter(description = "id of user", example = "1") @PathVariable("user-id") Long userId);




}
