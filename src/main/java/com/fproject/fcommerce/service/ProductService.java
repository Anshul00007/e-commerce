    package com.fproject.fcommerce.service;
    import java.util.List;
    import java.util.Optional;
    import org.springframework.stereotype.Service;
    import com.fproject.fcommerce.dto.ProductRequestDTO;
    import com.fproject.fcommerce.dto.ProductResponseDTO;
    import com.fproject.fcommerce.entity.Category;
    import com.fproject.fcommerce.entity.Inventory;
    import com.fproject.fcommerce.entity.Product;
    import com.fproject.fcommerce.exception.CategoryInactiveException;
    import com.fproject.fcommerce.exception.CategoryNotFoundException;
    import com.fproject.fcommerce.exception.DuplicateResourceException;
    import com.fproject.fcommerce.exception.ProductInactiveException;
    import com.fproject.fcommerce.exception.ProductNotFoundException;
    import com.fproject.fcommerce.mapper.InventoryMapper;
    import com.fproject.fcommerce.mapper.ProductMapper;
    import com.fproject.fcommerce.repo.CategoryRepo;
    import com.fproject.fcommerce.repo.InventoryRepo;
    import com.fproject.fcommerce.repo.ProductRepo;

    @Service
    public class ProductService {

        private final ProductRepo productrepo;
        private final CategoryRepo categoryrepo;
            private final InventoryRepo inventoryrepo;


        public ProductService(ProductRepo productrepo, CategoryRepo categoryrepo,InventoryRepo inventoryrepo) {
            this.productrepo = productrepo;
            this.categoryrepo = categoryrepo;
            this.inventoryrepo =inventoryrepo;
        }

        private String generateSlug(String name) {
            return name.toLowerCase().trim().replaceAll("\\s+", "-");
        }

        public ProductResponseDTO createProduct(ProductRequestDTO dto) {
            Category c = categoryrepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
            if (!c.isActive()) {
                throw new CategoryInactiveException("Catgeory is not active");
            }
            String slug = generateSlug(dto.getName());
            if (productrepo.findBySlug(slug).isPresent()) {
                throw new DuplicateResourceException("slug already exists");
            }

            if (productrepo.findBySku(dto.getSku()).isPresent()) {
                throw new DuplicateResourceException("sku already exists");
            }
            Product p = ProductMapper.toEntity(dto, slug, c);
            Product saved = productrepo.save(p);
            Inventory inventory = InventoryMapper.toEntity(saved);

            inventoryrepo.save(inventory);
            return ProductMapper.toDTO(saved);

        }

        public List<ProductResponseDTO> getAllProducts() {
            List<Product> l = productrepo.findAll();

            List<ProductResponseDTO> res = l.stream()
                    .map(ProductMapper::toDTO)
                    .toList();
            return res;
        }

        public ProductResponseDTO getProductById(Long id) {
            Product p = productrepo.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            return ProductMapper.toDTO(p);
        }

        public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

            Product product = productrepo.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            Category category = categoryrepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

            if (!category.isActive()) {
                throw new CategoryInactiveException("Category is not active");
            }

            String slug = generateSlug(dto.getName());

            Optional<Product> optionalProduct = productrepo.findBySlug(slug);

            if (optionalProduct.isPresent()
                    && optionalProduct.get().getId() != id) {

                throw new DuplicateResourceException("Slug already exists");
            }

            product.setName(dto.getName());
            product.setSlug(slug);
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setCategory(category);

            Product saved = productrepo.save(product);
            return ProductMapper.toDTO(saved);
        }

        public void Deactivate(Long id) {
            Product p = productrepo.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            if (!p.isActive()) {
                throw new ProductInactiveException("product is already inactive");
            }

            p.setActive(false);
            productrepo.save(p);

        }

        public List<ProductResponseDTO> getActiveProducts(){
            List<Product> l=  productrepo.findByActiveTrue();
            List<ProductResponseDTO> res=l.stream()
            .map(ProductMapper::toDTO)
            .toList();
            return res;
        }

    }
