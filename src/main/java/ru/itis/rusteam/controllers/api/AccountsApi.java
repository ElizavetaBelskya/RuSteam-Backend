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
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.dto.account.NewOrUpdateAccountDto;
import ru.itis.rusteam.dto.exception.ExceptionDto;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Accounts")
})
@RequestMapping("/accounts")
public interface AccountsApi {


    @Operation(summary = "Добавление аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный аккаунт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Введенный email уже используется",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<AccountDto> addAccount(
            @Valid @RequestBody NewOrUpdateAccountDto newAccount);


    @Operation(summary = "Получение аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об аккаунте",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{account-id}")
    ResponseEntity<AccountDto> getAccount(
            @Parameter(description = "Идентификатор аккаунта", example = "1642")
            @PathVariable("account-id") Long accountId);


    @Operation(summary = "Обновление аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный аккаунт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{account-id}")
    ResponseEntity<AccountDto> updateAccount(
            @Parameter(description = "Идентификатор аккаунта", example = "1642") @PathVariable("account-id") Long accountId,
            @Valid @RequestBody NewOrUpdateAccountDto updatedAccount);


    @Operation(summary = "Удаление аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Аккаунт удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{account-id}")
    ResponseEntity<?> deleteAccount(
            @Parameter(description = "Идентификатор аккаунта", example = "1642") @PathVariable("account-id") Long accountId);


    @Operation(summary = "Подтверждение аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Подтвержденный аккаунт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{account-id}/publish")
    ResponseEntity<AccountDto> confirmAccount(
            @Parameter(description = "Идентификатор аккаунта", example = "1642") @PathVariable("account-id") Long accountId);


//    @PostMapping("/login")


}
