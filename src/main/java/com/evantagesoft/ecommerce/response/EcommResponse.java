package com.evantagesoft.ecommerce.response;


public enum EcommResponse {

    SUCCESS("SUCCESS_00", "Success"),
    USER_REGISTERED_SUCCESSFULLY("SUCCESS_USER_01", "User Registered Successfully"),
    INVALID_REQUEST_PARAMETER("ERROR_01", "Invalid request parameter"),
    DATA_NOT_FOUND("ERROR_02", "Data Not Found"),
    LOGIN_SUCCESSFUL("SUCCESS_USER_01", "User Login Successfully"),
    INVALID_CREDENTIALS("ERROR_03", "Invalid Credentials"),
    USER_NOT_FOUND("ERROR_04", "User Not Found"),
    OTP_SEND_SUCCESSFULLY("SUCCESS_USER_01", "OTP Send Successfully."),
    PASSWORD_UPDATED_SUCCESSFULLY("SUCCESS_USER_01", "Password Updated Successfully.");



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
