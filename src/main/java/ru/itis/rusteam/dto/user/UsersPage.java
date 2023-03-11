package ru.itis.rusteam.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author Elizaveta Belskaya
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Page with users")
public class UsersPage {
    @Schema(description = "list of users")
    private List<UserDto> users;

    @Schema(description = "total amount of pages", example = "1")
    private Integer totalPagesCount;

}
