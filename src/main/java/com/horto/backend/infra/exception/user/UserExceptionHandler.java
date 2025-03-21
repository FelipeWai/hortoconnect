package com.horto.backend.infra.exception.user;

import com.horto.backend.core.exceptions.subcategory.SubcategoryAlreadyExistsException;
import com.horto.backend.core.exceptions.user.UsernameOrPasswordInvalidException;
import com.horto.backend.core.exceptions.user.alreadyExists.CnpjAlreadyExistsException;
import com.horto.backend.core.exceptions.user.alreadyExists.EmailAlreadyExistsException;
import com.horto.backend.core.exceptions.user.alreadyExists.PhoneAlreadyExistsException;
import com.horto.backend.core.exceptions.user.alreadyExists.UsernameAlreadyExistsException;
import com.horto.backend.core.exceptions.user.notFound.CnpjNotFoundException;
import com.horto.backend.core.exceptions.user.notFound.EmailNotFoundException;
import com.horto.backend.core.exceptions.user.notFound.PhoneNotFoundException;
import com.horto.backend.core.exceptions.user.notFound.UsernameNotFoundException;
import com.horto.backend.infra.exception.global.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(EmailNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CnpjNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(CnpjNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(PhoneNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(UsernameNotFoundException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(EmailAlreadyExistsException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CnpjAlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(CnpjAlreadyExistsException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(PhoneAlreadyExistsException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(UsernameAlreadyExistsException ex) {
        GlobalExceptionHandler.ErrorResponse error = new GlobalExceptionHandler.ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<GlobalExceptionHandler.ErrorResponse> handleCategoryNotFoundException(UsernameOrPasswordInvalidException ex) {
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
