package com.horto.backend.infra.exception.quality;

import com.horto.backend.core.exceptions.quality.QualityAlreadyExists;
import com.horto.backend.core.exceptions.quality.QualityNotFoundException;
import com.horto.backend.infra.exception.global.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QualityExceptionHandler {

    @ExceptionHandler(QualityNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(QualityNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QualityAlreadyExists.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryAlreadyExists(QualityAlreadyExists ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
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
