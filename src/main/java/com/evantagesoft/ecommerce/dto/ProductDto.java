package com.evantagesoft.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private String category;
    private int stockQuantity;
}
