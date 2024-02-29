package me.jazzy.librarymanagementsystem.exception.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandling(BaseException baseException) {

        ErrorResponse errorResponse = new ErrorResponse(
                baseException.getHttpStatus().value(),
                baseException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, baseException.getHttpStatus());
    }
}
