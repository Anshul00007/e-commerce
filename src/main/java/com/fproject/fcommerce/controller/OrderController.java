    package com.fproject.fcommerce.controller;

    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestHeader;

    import com.fproject.fcommerce.dto.OrderResponseDTO;
    import com.fproject.fcommerce.service.OrderService;

    import java.util.List;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;

    @RestController
    @RequestMapping("/api/v1/orders")
    public class OrderController {
        private final OrderService orderService;
        
        public OrderController(OrderService orderService){
            this.orderService = orderService;
        }
        
        @GetMapping
        public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
            return ResponseEntity.ok(orderService.getOrders());
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<OrderResponseDTO> getOrdersById(@PathVariable Long id) {
            return ResponseEntity.ok(orderService.getOrderById(id));
        }
        
        @PostMapping("/place")
        public ResponseEntity<OrderResponseDTO> placeOrders(
                @RequestHeader("Idempotency-Key") String idempotencyKey) {
            return ResponseEntity.status(201).body(orderService.placeOrder(idempotencyKey));
        }
    }
