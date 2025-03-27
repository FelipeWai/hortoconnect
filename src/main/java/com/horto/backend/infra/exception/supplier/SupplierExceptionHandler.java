package com.horto.backend.infra.exception.supplier;

import com.horto.backend.core.exceptions.subcategory.SubcategoryAlreadyExistsException;
import com.horto.backend.core.exceptions.subcategory.SubcategoryNotFoundException;
import com.horto.backend.core.exceptions.supplier.SupplierNameAlreadyExistsException;
import com.horto.backend.core.exceptions.supplier.SupplierNotFoundException;
import com.horto.backend.infra.exception.global.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SupplierExceptionHandler {

    @ExceptionHandler(SupplierNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(SupplierNameAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(SupplierNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
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
