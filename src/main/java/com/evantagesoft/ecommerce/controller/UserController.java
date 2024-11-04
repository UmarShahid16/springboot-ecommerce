package com.evantagesoft.ecommerce.controller;

import com.evantagesoft.ecommerce.dto.UserDto;
import com.evantagesoft.ecommerce.entity.User;
import com.evantagesoft.ecommerce.response.Response;
import com.evantagesoft.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response createUser(@RequestBody UserDto userDto){

            return userService.registerUser(userDto);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        try {
           Response response = userService.loginUser(userDto);
           return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/verifyEmail")
    public Response verifyEmail(@RequestBody UserDto userDto) throws Exception {
        String email = userDto.getEmail();
        return userService.verifyEmail(email);
    }

    @PostMapping("/sendOTP")
    public Response sendOtp(@RequestBody UserDto userDto){
        String email = userDto.getEmail();
        return userService.sendOtp(email);
    }

    @PostMapping("/verifyOTP")
    public Response verifyOtp(@RequestBody UserDto userDto){
        int otp = userDto.getOtp();
        String email = userDto.getEmail();
        return userService.verifyOtp(email, otp);
    }

    @PostMapping("/updatePassword")
    public Response updatePassword(@RequestBody UserDto userDto){
        return userService.updatePassword(userDto);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto){
        try {
            String result = userService.deleteUser(userDto);
            return ResponseEntity.ok(result);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


