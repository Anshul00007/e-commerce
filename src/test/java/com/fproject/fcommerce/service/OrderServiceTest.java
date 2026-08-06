package com.fproject.fcommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fproject.fcommerce.dto.OrderResponseDTO;
import com.fproject.fcommerce.entity.Cart;
import com.fproject.fcommerce.entity.CartItem;
import com.fproject.fcommerce.entity.Inventory;
import com.fproject.fcommerce.entity.Order;
import com.fproject.fcommerce.entity.Product;
import com.fproject.fcommerce.entity.User;
import com.fproject.fcommerce.exception.CartNotFoundException;
import com.fproject.fcommerce.exception.InsufficientStockException;
import com.fproject.fcommerce.repo.CartRepo;
import com.fproject.fcommerce.repo.IdempotencyRecordRepository;
import com.fproject.fcommerce.repo.InventoryRepo;
import com.fproject.fcommerce.repo.OrderItemRepo;
import com.fproject.fcommerce.repo.OrderRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock private CartRepo cartRepo;
    @Mock private OrderRepo orderRepo;
    @Mock private InventoryRepo inventoryRepo;
    @Mock private OrderItemRepo orderItemRepo;
    @Mock private IdempotencyRecordRepository idempotencyRepo;
    @Mock private SecurityContext securityContext;
    @Mock private Authentication authentication;

    @InjectMocks private OrderService orderService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("developer@example.com");
        
        // Mock the Spring Security Session Context
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        lenient().when(authentication.getPrincipal()).thenReturn(mockUser);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testPlaceOrder_SuccessFlow() {
        Cart mockCart = new Cart();
        mockCart.setActive(true);
        mockCart.setUser(mockUser);

        Product mockProduct = new Product();
        mockProduct.setId(100L);
        mockProduct.setName("Premium Laptop");
        mockProduct.setPrice(BigDecimal.valueOf(1000));

        CartItem mockItem = new CartItem();
        mockItem.setProduct(mockProduct);
        mockItem.setQuantity(2);
        
        List<CartItem> items = new ArrayList<>();
        items.add(mockItem);
        mockCart.setItems(items);

        Inventory mockInventory = new Inventory();
        mockInventory.setStock(10);

        when(idempotencyRepo.findByUserAndIdempotencyKey(any(), any())).thenReturn(Optional.empty());
        when(cartRepo.findByUserAndActiveTrue(mockUser)).thenReturn(Optional.of(mockCart));
        when(inventoryRepo.findByProductIdForUpdate(100L)).thenReturn(Optional.of(mockInventory));
        when(orderRepo.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderResponseDTO result = orderService.placeOrder("sample-token-key-123");

        assertNotNull(result);
        assertEquals(8, mockInventory.getStock()); 
        verify(orderRepo, times(1)).save(any(Order.class));
    }

    @Test
    public void testPlaceOrder_InsufficientStock_ThrowsException() {
        Cart mockCart = new Cart();
        mockCart.setActive(true);

        Product mockProduct = new Product();
        mockProduct.setId(200L);
        mockProduct.setName("Out Of Stock Shoes");

        CartItem mockItem = new CartItem();
        mockItem.setProduct(mockProduct);
        mockItem.setQuantity(5);
        
        mockCart.setItems(List.of(mockItem));

        Inventory limitedInventory = new Inventory();
        limitedInventory.setStock(2); // Only 2 left, customer wants 5

        when(idempotencyRepo.findByUserAndIdempotencyKey(any(), any())).thenReturn(Optional.empty());
        when(cartRepo.findByUserAndActiveTrue(mockUser)).thenReturn(Optional.of(mockCart));
        when(inventoryRepo.findByProductIdForUpdate(200L)).thenReturn(Optional.of(limitedInventory));

        assertThrows(InsufficientStockException.class, () -> {
            orderService.placeOrder("sample-token-key-456");
        });
    }
}
