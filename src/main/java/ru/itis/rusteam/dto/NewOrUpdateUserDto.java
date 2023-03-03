package ru.itis.rusteam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author Elizaveta Belskaya
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "New user")
public class NewOrUpdateUserDto {
    @Schema(description = "name of user", example = "John")
    private String nickname;
    @Schema(description = "email of user", example = "john@gmail.com")
    private String email;

}
