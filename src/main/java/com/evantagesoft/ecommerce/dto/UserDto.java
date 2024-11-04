package com.evantagesoft.ecommerce.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Integer otp;
}
