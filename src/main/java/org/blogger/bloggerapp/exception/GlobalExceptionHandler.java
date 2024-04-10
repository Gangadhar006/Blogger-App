package org.blogger.bloggerapp.exception;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import org.blogger.bloggerapp.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArguementException(Exception exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> invalidCredentialsException(Exception exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(Exception exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ErrorResponse> blogNotFoundException(Exception exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(Exception exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleBadRequestExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        for (ObjectError error : errorList) {
            errorResponseList.add(
                    new ErrorResponse(error.getDefaultMessage(), HttpStatus.BAD_REQUEST)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseList);
    }
}