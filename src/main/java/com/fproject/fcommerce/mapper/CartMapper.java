package com.fproject.fcommerce.mapper;

import java.util.List;

import com.fproject.fcommerce.dto.CartItemResponseDTO;
import com.fproject.fcommerce.dto.CartResponseDTO;

import com.fproject.fcommerce.entity.Cart;
import com.fproject.fcommerce.entity.CartItem;
;

public class CartMapper {
    public static CartItemResponseDTO toCartItemDTO(CartItem item){
          CartItemResponseDTO dto = new CartItemResponseDTO();
          dto.setId(item.getId());
       dto.setQuantity(item.getQuantity());
dto.setProductId(item.getProduct().getId());
dto.setProductName(item.getProduct().getName());

          return dto;

    }

   public static CartResponseDTO toDTO(Cart cart){
       CartResponseDTO dto = new CartResponseDTO();

       dto.setId(cart.getId());
       dto.setCreatedAt(cart.getCreatedAt());
       dto.setUpdatedAt(cart.getUpdatedAt());
       dto.setActive(cart.isActive());

     List<CartItemResponseDTO> c= cart.getItems()
     .stream()
      .map(CartMapper::toCartItemDTO)
      .toList();
      dto.setItems(c);
    return dto;
    }


}
