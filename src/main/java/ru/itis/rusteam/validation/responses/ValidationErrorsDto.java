package ru.itis.rusteam.validation.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorsDto {

    @Schema(description = "Ошибки валидации")
    private List<ValidationErrorDto> errors;
}
