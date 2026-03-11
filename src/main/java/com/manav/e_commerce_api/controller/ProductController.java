package com.manav.e_commerce_api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manav.e_commerce_api.dto.ProductDTO;
import com.manav.e_commerce_api.entity.Category;
import com.manav.e_commerce_api.entity.Product;
import com.manav.e_commerce_api.repository.CategoryRepository;
import com.manav.e_commerce_api.repository.ProductRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody ProductDTO productDto) {
        Product product = new Product();
        mapDtoToEntity(productDto, product);
        return Objects.requireNonNull(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDto) {
        Long safeId = Objects.requireNonNull(id, "ID must not be null");

        return productRepository.findById(safeId)
                .map(product -> {
                    mapDtoToEntity(productDto, product);
                    return Objects.requireNonNull(productRepository.save(product));
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + safeId));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Long safeId = Objects.requireNonNull(id, "ID must not be null");

        Product product = productRepository.findById(safeId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + safeId));
        
        productRepository.delete(product);
        return "Product deleted successfully!";
    }

    private void mapDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));
            product.setCategory(category);
        }
    }
}