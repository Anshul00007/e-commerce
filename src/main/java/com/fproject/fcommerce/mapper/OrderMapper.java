package com.fproject.fcommerce.mapper;
import java.util.List;

import com.fproject.fcommerce.dto.OrderItemResponseDTO;
import com.fproject.fcommerce.dto.OrderResponseDTO;

import com.fproject.fcommerce.entity.Order;
import com.fproject.fcommerce.entity.OrderItem;

public class OrderMapper {
    public static OrderItemResponseDTO toOrderItemDTO(OrderItem item) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setItemTotal(item.getItemTotal());
        dto.setPriceAtOrderTime(item.getPriceAtOrderTime());
        return dto;
     
   
    }

    public static OrderResponseDTO toDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setPlacedAt(order.getPlacedAt());
        List<OrderItemResponseDTO> c = order.getItems()
                .stream()
                .map(OrderMapper::toOrderItemDTO)
                .toList();
        dto.setItems(c);
        return dto;

  

    }

}
