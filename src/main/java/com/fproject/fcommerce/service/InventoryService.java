package com.fproject.fcommerce.service;

import org.springframework.stereotype.Service;

import com.fproject.fcommerce.dto.InventoryRequestDTO;
import com.fproject.fcommerce.dto.InventoryResponseDTO;
import com.fproject.fcommerce.entity.Inventory;
import com.fproject.fcommerce.exception.InventoryNotFoundException;
import com.fproject.fcommerce.mapper.InventoryMapper;
import com.fproject.fcommerce.repo.InventoryRepo;


@Service
public class InventoryService {
    private final InventoryRepo inventoryrepo;
   

    public InventoryService(InventoryRepo inventoryrepo) {
        this.inventoryrepo = inventoryrepo;
      
    }

    public InventoryResponseDTO getInventoryByProductId(Long productId) {

        Inventory inventory = inventoryrepo.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        return InventoryMapper.toDTO(inventory);
    }

    public InventoryResponseDTO updateStock(Long productId, InventoryRequestDTO dto) {

        Inventory inventory = inventoryrepo.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        inventory.setStock(dto.getStock());

        Inventory updatedInvetory = inventoryrepo.save(inventory);

        return InventoryMapper.toDTO(updatedInvetory);

    }

}
