package com.evantagesoft.ecommerce.service.user;

import com.evantagesoft.ecommerce.dto.UserDto;
import com.evantagesoft.ecommerce.entity.User;
import com.evantagesoft.ecommerce.response.Response;

public interface UserService {


    Response registerUser(UserDto userDto);

    Response loginUser(UserDto userDto) throws  Exception;


    Response verifyEmail(String email);

    Response sendOtp(String email);

    Response verifyOtp(String email, int otp);

    Response updatePassword(UserDto userDto);

    String deleteUser(UserDto userDto) throws  Exception;
}
