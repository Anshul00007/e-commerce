package com.fproject.fcommerce.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String m) {
        super(m);
    }
}