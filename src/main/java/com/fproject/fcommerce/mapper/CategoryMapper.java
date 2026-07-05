package com.fproject.fcommerce.mapper;

import com.fproject.fcommerce.dto.CategoryRequestDTO;
import com.fproject.fcommerce.dto.CategoryResponseDTO;
import com.fproject.fcommerce.entity.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryRequestDTO dto,String slug){
          Category c = new Category();
          c.setName(dto.getName());
          c.setSlug(slug);
          return c;
    }
    public static CategoryResponseDTO toDTO(Category category){
       CategoryResponseDTO dto = new CategoryResponseDTO();

       dto.setId(category.getId());
       dto.setName(category.getName());
       dto.setSlug(category.getSlug());
       dto.setActive(category.isActive());
       dto.setCreatedAt(category.getCreatedAt());
       dto.setUpdatedAt(category.getUpdatedAt());
    return dto;
    }
}
