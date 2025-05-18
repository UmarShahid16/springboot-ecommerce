package com.evantagesoft.ecommerce.response;

import com.evantagesoft.ecommerce.dto.UserDto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    private String code;
    private String message;
    private Map<String, Object> responseData;

    public Response() { }
    public Response(String code, String message){
        this.code = code;
        this.message = message;
    }

    public void setResponse(EcommResponse response) {
        this.code = response.getCode();
        this.message = response.getMessage();
    }

    public void setData(String key, Object obj) {
        if (this.responseData == null) {
            this.responseData = new HashMap<>();
        }
        this.responseData.put(key, obj);
    }
}
