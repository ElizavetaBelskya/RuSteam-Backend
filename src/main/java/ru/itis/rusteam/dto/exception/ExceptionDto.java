package ru.itis.rusteam.dto.exception;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация об ошибке")
public class ExceptionDto {

    @Schema(description = "Сообщение", example = "Ресурс не найден")
    private String message;
    @Schema(description = "HTTP-код", example = "404")
    private int status;


}
