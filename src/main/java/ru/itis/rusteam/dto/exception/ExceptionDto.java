package ru.itis.rusteam.dto.exception;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
/**
 * @author Elizaveta Belskaya
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Data about errors")
public class ExceptionDto {

    @Schema(description = "Error message", example = "User not found")
    private String message;
    @Schema(description = "HTTP-status of error", example = "404")
    private int status;


}
