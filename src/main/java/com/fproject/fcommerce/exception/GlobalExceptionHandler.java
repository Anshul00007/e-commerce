package com.fproject.fcommerce.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fproject.fcommerce.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponseDto> handleOptimisticLockingFailure(ObjectOptimisticLockingFailureException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(409);
        errorDto.setError("Stock was updated by another request. Please retry.");

        return ResponseEntity.status(409).body(errorDto);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDto> slugExistException(DuplicateResourceException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(409);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(409).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> noValidArgumentException(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        
        for (FieldError err : e.getBindingResult().getFieldErrors()) {
            error.put(err.getField(), err.getDefaultMessage());
        }

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCategoryNotFound(CategoryNotFoundException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(404);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(CategoryInactiveException.class)
    public ResponseEntity<ErrorResponseDto> handleCategoryInactive(CategoryInactiveException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(409);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(409).body(errorDto);
    }

    @ExceptionHandler(ProductInactiveException.class)
    public ResponseEntity<ErrorResponseDto> handleProductInactive(ProductInactiveException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(409);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(409).body(errorDto);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFound(ProductNotFoundException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(404);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleInventoryNotFound(InventoryNotFoundException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(404);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCartNotFound(CartNotFoundException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(404);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCartItemNotFound(CartItemNotFoundException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(404);
        errorDto.setError(e.getMessage());

        return ResponseEntity.status(404).body(errorDto);
    }
}
