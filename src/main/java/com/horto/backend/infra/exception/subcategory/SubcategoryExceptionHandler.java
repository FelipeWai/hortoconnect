package com.horto.backend.infra.exception.subcategory;

import com.horto.backend.core.exceptions.size.SizeAlreadyExists;
import com.horto.backend.core.exceptions.size.SizeNotFoundException;
import com.horto.backend.core.exceptions.subcategory.SubcategoryNotFoundException;
import com.horto.backend.infra.exception.global.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubcategoryExceptionHandler {

    @ExceptionHandler(SubcategoryNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(SubcategoryNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
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
