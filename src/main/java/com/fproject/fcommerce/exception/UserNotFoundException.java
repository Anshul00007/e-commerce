package com.fproject.fcommerce.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String m) {
        super(m);
    }
}