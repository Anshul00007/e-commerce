package com.fproject.fcommerce.exception;

public class CategoryNotFoundException extends RuntimeException  {
   public CategoryNotFoundException(String message){
        super(message);
    }
}
