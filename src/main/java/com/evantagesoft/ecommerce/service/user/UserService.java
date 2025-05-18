package com.evantagesoft.ecommerce.service.user;

import com.evantagesoft.ecommerce.dto.UserDto;
import com.evantagesoft.ecommerce.entity.User;
import com.evantagesoft.ecommerce.response.Response;

public interface UserService {


    Response registerUser(UserDto userDto);

    Response loginUser(UserDto userDto) throws  Exception;


    Response verifyEmail(UserDto userDto);

    Response sendOtp(UserDto userDto);

    Response verifyOtp(UserDto userDto);

    Response updatePassword(UserDto userDto);

    Response deleteUser(UserDto userDto);
}
