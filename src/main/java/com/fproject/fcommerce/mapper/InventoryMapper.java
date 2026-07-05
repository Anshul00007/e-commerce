package com.fproject.fcommerce.mapper;
import com.fproject.fcommerce.dto.InventoryResponseDTO;
import com.fproject.fcommerce.entity.Inventory;
import com.fproject.fcommerce.entity.Product;

public class InventoryMapper {
     public static Inventory toEntity(Product product){
          Inventory I = new Inventory();
          I.setProduct(product);
          I.setStock(0);
        

          return I;

    }

    public static InventoryResponseDTO toDTO(Inventory inventory){
       InventoryResponseDTO dto = new InventoryResponseDTO();

       dto.setId(inventory.getId());
       dto.setCreatedAt(inventory.getCreatedAt());
       dto.setUpdatedAt(inventory.getUpdatedAt());
       dto.setStock(inventory.getStock());
       dto.setProductId(inventory.getProduct().getId());
    return dto;
    }




}
