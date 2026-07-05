package com.fproject.fcommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fproject.fcommerce.dto.OrderResponseDTO;
import com.fproject.fcommerce.entity.Cart;
import com.fproject.fcommerce.entity.CartItem;
import com.fproject.fcommerce.entity.Inventory;
import com.fproject.fcommerce.entity.Order;
import com.fproject.fcommerce.entity.OrderItem;
import com.fproject.fcommerce.entity.OrderStatus;
import com.fproject.fcommerce.entity.User;
import com.fproject.fcommerce.entity.IdempotencyRecord;
import com.fproject.fcommerce.exception.CartNotFoundException;
import com.fproject.fcommerce.mapper.OrderMapper;
import com.fproject.fcommerce.repo.CartRepo;
import com.fproject.fcommerce.repo.InventoryRepo;
import com.fproject.fcommerce.repo.OrderItemRepo;
import com.fproject.fcommerce.repo.OrderRepo;
import com.fproject.fcommerce.repo.IdempotencyRecordRepository;

@Service
public class OrderService {
    private final CartRepo cartrepo;
    private final OrderRepo orderrepo;
    private final InventoryRepo inventoryrepo;
    private final OrderItemRepo orderitemrepo;
    private final IdempotencyRecordRepository idempotencyRepo;

    public OrderService(CartRepo cartrepo, OrderRepo orderrepo, InventoryRepo inventoryrepo, 
                        OrderItemRepo orderitemrepo, IdempotencyRecordRepository idempotencyRepo) {
        this.cartrepo = cartrepo;
        this.orderrepo = orderrepo;
        this.inventoryrepo = inventoryrepo;
        this.orderitemrepo = orderitemrepo;
        this.idempotencyRepo = idempotencyRepo;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Transactional
    public OrderResponseDTO placeOrder(String idempotencyKey) {
        User user = getCurrentUser();

        Optional<IdempotencyRecord> existingRecord = idempotencyRepo.findByUserAndIdempotencyKey(user, idempotencyKey);
        if (existingRecord.isPresent()) {
            return OrderMapper.toDTO(existingRecord.get().getOrder());
        }

        Cart cart = cartrepo.findByUserAndActiveTrue(user)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cannot place order: Cart is empty");
        }

        Order order = new Order();
        order.setPlacedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        order.setUser(user);
        
        BigDecimal calculatedTotalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItemsList = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Inventory inventory = inventoryrepo.findByProductIdForUpdate(cartItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Inventory details not found for product: " + cartItem.getProduct().getName()));
            
            if (inventory.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + cartItem.getProduct().getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order); 
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtOrderTime(cartItem.getProduct().getPrice());

            BigDecimal itemTotal = orderItem.getPriceAtOrderTime().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            orderItem.setItemTotal(itemTotal);

            inventory.setStock(inventory.getStock() - cartItem.getQuantity());
            inventoryrepo.save(inventory);

            calculatedTotalPrice = calculatedTotalPrice.add(itemTotal);
            orderItemsList.add(orderItem);
        }

        order.setTotalPrice(calculatedTotalPrice);
        order.setItems(orderItemsList);

        Order savedOrder = orderrepo.save(order);
        
        for (OrderItem oi : orderItemsList) {
            orderitemrepo.save(oi);
        }

        cart.setActive(false);
        cartrepo.save(cart);

        IdempotencyRecord record = new IdempotencyRecord();
        record.setIdempotencyKey(idempotencyKey);
        record.setUser(user);
        record.setOrder(savedOrder);
        idempotencyRepo.save(record);

        return OrderMapper.toDTO(savedOrder);
    }

    public List<OrderResponseDTO> getOrders() {
        User user = getCurrentUser();
        List<Order> l = orderrepo.findByUser(user);
        return l.stream().map(OrderMapper::toDTO).toList();
    }

    public OrderResponseDTO getOrderById(Long id) {
        User currentUser = getCurrentUser();
        Order order = orderrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found"));
        if (!order.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("access denied");
        }
        return OrderMapper.toDTO(order);
    }
}
