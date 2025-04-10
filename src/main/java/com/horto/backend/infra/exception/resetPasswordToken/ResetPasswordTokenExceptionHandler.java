package com.horto.backend.infra.exception.resetPasswordToken;

import com.horto.backend.core.exceptions.quality.QualityAlreadyExists;
import com.horto.backend.core.exceptions.resetPasswordToken.NewPasswordSameAsOldException;
import com.horto.backend.core.exceptions.resetPasswordToken.TokenNotFoundException;
import com.horto.backend.infra.exception.global.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ResetPasswordTokenExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExists(HttpClientErrorException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NewPasswordSameAsOldException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExists(NewPasswordSameAsOldException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResponse {
        private final int status;
        private final String message;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

}
