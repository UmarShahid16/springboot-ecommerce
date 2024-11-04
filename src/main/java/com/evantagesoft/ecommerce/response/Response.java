package com.evantagesoft.ecommerce.response;

import com.evantagesoft.ecommerce.dto.UserDto;
import lombok.Data;

@Data
public class Response {
    private String code;
    private String message;


    public Response(String code, String message){
        this.code = code;
        this.message = message;
    }
}
