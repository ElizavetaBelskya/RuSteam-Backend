package ru.itis.rusteam.validation.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.rusteam.validation.responses.ValidationErrorDto;
import ru.itis.rusteam.validation.responses.ValidationErrorsDto;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleNotFound(MethodArgumentNotValidException ex) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
            
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .field(fieldName)
                    .build();
            errors.add(errorDto);
        });
        return ResponseEntity.ok(ValidationErrorsDto.builder()
                .errors(errors)
                .build());
    }
}
