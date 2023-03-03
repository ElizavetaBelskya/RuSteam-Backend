package ru.itis.rusteam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.itis.rusteam.models.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elizaveta Belskaya
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "User")
public class UserDto {
    @Schema(description = "id of user", example = "1")
    private Long id;
    @Schema(description = "name of user", example = "John")
    private String nickname;
    @Schema(description = "email of user", example = "john@gmail.com")
    private String email;

    @Schema(description = "status of user, his personal information", example = "I am alive")
    private String status;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

}

