package com.horto.backend.infra.exception.supplierOffer;

import com.horto.backend.core.exceptions.supplier.SupplierNotFoundException;
import com.horto.backend.core.exceptions.supplierOffer.OffersNotFoundException;
import com.horto.backend.core.exceptions.supplierOffer.OneOfferNotFoundException;
import com.horto.backend.infra.exception.supplier.SupplierExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SupplierOfferExceptionHandler {

    @ExceptionHandler(OffersNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(OffersNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OneOfferNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(OneOfferNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
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
