package com.evantagesoft.ecommerce.response;


public enum EcommResponse {

    SUCCESS("SUCCESS_00", "Success"),
    USER_REGISTERED_SUCCESSFULLY("SUCCESS_USER_01", "User registered Successfully"),
    INVALID_REQUEST_PARAMETER("ERROR_01", "Invalid request parameter"),
    DATA_NOT_FOUND("ERROR_02", "Data Not Found"),
    LOGIN_SUCCESSFUL("SUCCESS_USER_01", "User Login Successfully"),
    INVALID_CREDENTIALS("ERROR_03", "Invalid Credentials"),
    USER_NOT_FOUND("ERROR_04", "User Not Found");



    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    EcommResponse(String code, String message){
        this.code = code;
        this.message = message;
    }
}
