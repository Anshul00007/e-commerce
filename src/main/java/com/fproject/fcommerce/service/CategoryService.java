package com.fproject.fcommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fproject.fcommerce.dto.CategoryRequestDTO;
import com.fproject.fcommerce.dto.CategoryResponseDTO;
import com.fproject.fcommerce.entity.Category;
import com.fproject.fcommerce.exception.CategoryInactiveException;
import com.fproject.fcommerce.exception.CategoryNotFoundException;
import com.fproject.fcommerce.exception.DuplicateResourceException;
import com.fproject.fcommerce.mapper.CategoryMapper;
import com.fproject.fcommerce.repo.CategoryRepo;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    private String generateSlug(String name) {
        return name.toLowerCase().trim().replaceAll("\\s+", "-");
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

        String slug = generateSlug(dto.getName());
        if (categoryRepo.findBySlug(slug).isPresent()) {
            throw new DuplicateResourceException("Category already exists");
        }
        Category category = CategoryMapper.toEntity(dto, slug);
        Category saved = categoryRepo.save(category);
        return CategoryMapper.toDTO(saved);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> c = categoryRepo.findAll();

        List<CategoryResponseDTO> res = c.stream()
                .map(CategoryMapper::toDTO)
                .toList();
        return res;

    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return CategoryMapper.toDTO(c);
    }

    public CategoryResponseDTO getCategoryBySlug(String slug) {
        Category c = categoryRepo.findBySlug(slug)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return CategoryMapper.toDTO(c);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        String n = dto.getName();
        String slug = generateSlug(n);
        Optional<Category> optionalCategory = categoryRepo.findBySlug(slug);

        if (optionalCategory.isPresent() && optionalCategory.get().getId() != id) {
            throw new DuplicateResourceException("Slug already exists in another category");
        }

        c.setSlug(slug);
        c.setName(n);

        Category saved = categoryRepo.save(c);
        return CategoryMapper.toDTO(saved);

    }

    public void Deactivate(Long id) {
        Category c = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        if (c.isActive() == false) {
            throw new CategoryInactiveException("Category is already deactivated");
        }
        c.setActive(false);
        categoryRepo.save(c);

    }

    public List<CategoryResponseDTO> getActiveCategory(){
        List<Category> l=  categoryRepo.findByActiveTrue();
        List<CategoryResponseDTO> res=l.stream()
        .map(CategoryMapper::toDTO)
        .toList();
        return res;
    }

}
