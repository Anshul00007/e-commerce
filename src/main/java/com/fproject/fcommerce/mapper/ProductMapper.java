package com.fproject.fcommerce.mapper;



import com.fproject.fcommerce.dto.ProductRequestDTO;
import com.fproject.fcommerce.dto.ProductResponseDTO;
import com.fproject.fcommerce.entity.Category;
import com.fproject.fcommerce.entity.Product;



public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto,String slug,Category category){
          Product p = new Product();
          p.setName(dto.getName());
          p.setSlug(slug);
          p.setDescription(dto.getDescription());
          p.setSku(dto.getSku());
          p.setPrice(dto.getPrice());
          p.setCategory(category);

          return p;

    }
    public static ProductResponseDTO toDTO(Product product){
       ProductResponseDTO dto = new ProductResponseDTO();

       dto.setId(product.getId());
       dto.setName(product.getName());
       dto.setSlug(product.getSlug());
       dto.setActive(product.isActive());
       dto.setCreatedAt(product.getCreatedAt());
       dto.setUpdatedAt(product.getUpdatedAt());
       dto.setDescription(product.getDescription());
       dto.setSku(product.getSku());
       dto.setPrice(product.getPrice());
       dto.setCategoryId(product.getCategory().getId());
       
    return dto;
    }
}
