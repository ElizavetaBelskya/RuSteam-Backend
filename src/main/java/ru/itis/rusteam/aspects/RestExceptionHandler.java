package ru.itis.rusteam.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.rusteam.dto.exception.ExceptionDto;
import ru.itis.rusteam.exceptions.NotFoundException;

/**
 * @author Elizaveta Belskaya
 */


@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionDto> handleOther() {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ExceptionDto.builder()
//                        .message("Server error")
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .build());
//    }
//


}
