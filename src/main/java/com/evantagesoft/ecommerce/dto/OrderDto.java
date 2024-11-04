package com.evantagesoft.ecommerce.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;

    private Date orderDate;
    private int totalPrice;

    private UserDto userDto;

    private List<OrderItemDto> orderItemDtoList;
}
