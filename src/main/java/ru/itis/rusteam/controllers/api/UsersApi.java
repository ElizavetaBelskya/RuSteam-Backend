package ru.itis.rusteam.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "users")
})
@RequestMapping("/users")
public interface UsersApi {

    @Operation(summary = "Добавление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный пользователь",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<UserDto> addUser(
            @Valid @RequestBody NewOrUpdateUserDto newUser);
}
