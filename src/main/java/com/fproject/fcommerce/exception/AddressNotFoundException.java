package com.fproject.fcommerce.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String m) {
        super(m);
    }
}