package com.evantagesoft.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long categoryId; // This will be used to link to the Category
    private int stockQuantity;
}
