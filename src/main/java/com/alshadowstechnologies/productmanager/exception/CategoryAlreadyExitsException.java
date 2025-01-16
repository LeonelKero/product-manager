package com.alshadowstechnologies.productmanager.exception;

public class CategoryAlreadyExitsException extends RuntimeException {
    public CategoryAlreadyExitsException(String message) {
        super(message);
    }
}
