package com.manav.e_commerce_api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ... (Keep your @Id and @GeneratedValue at the top)

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Product name cannot be empty")
    private String name;

    private String description;

    @Column(nullable = false)
    @jakarta.validation.constraints.Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    @Column(nullable = false)
    @jakarta.validation.constraints.Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    // ... (Keep all your existing getters, setters, and constructors below this)

    // Empty Constructor (Required by Hibernate)
    public Product() {}

    // Getters and Setters (So Spring Boot can read/write the data)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
}