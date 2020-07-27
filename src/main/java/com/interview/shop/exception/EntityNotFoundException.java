package com.interview.shop.exception;

public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String s) {
        super(s);
    }
}
