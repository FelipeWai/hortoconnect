package com.horto.backend.infra.exception.product;

import com.horto.backend.core.exceptions.product.ProductAlreadyExists;
import com.horto.backend.core.exceptions.product.ProductNotFoundException;
import com.horto.backend.core.exceptions.quality.QualityAlreadyExists;
import com.horto.backend.core.exceptions.quality.QualityNotFoundException;
import com.horto.backend.infra.exception.global.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(ProductNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryAlreadyExists(ProductAlreadyExists ex) {
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
