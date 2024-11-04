package com.evantagesoft.ecommerce.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private int quantity;

    private OrderDto orderDto;

    private ProductDto productDto;
}
