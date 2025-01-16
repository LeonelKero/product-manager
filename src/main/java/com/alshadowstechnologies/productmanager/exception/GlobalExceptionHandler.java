package com.alshadowstechnologies.productmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> productNotFoundHandler(final ProductNotFoundException ex) {
        final var apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Product Resource Not Found");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> categoryNotFoundHandler(final CategoryNotFoundException ex) {
        final var apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Category Resource Not Found");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CategoryAlreadyExitsException.class})
    public ResponseEntity<ApiErrorResponse> categoryAlreadyExistsExceptionHandler(final CategoryAlreadyExitsException ex) {
        final var apiError = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), "Category already exists");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException ex) {
        final var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        ;
        final var apiError = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "", String.join(", ", errors));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
