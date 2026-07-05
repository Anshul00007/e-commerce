package com.fproject.fcommerce.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
}
}