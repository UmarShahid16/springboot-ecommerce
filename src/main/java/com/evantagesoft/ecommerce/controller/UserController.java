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
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){

        Response response = userService.registerUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);


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
    public ResponseEntity<?> verifyEmail(@RequestBody UserDto userDto){
        Response response = userService.sendOtp(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/sendOTP")
    public ResponseEntity<?> sendOtp(@RequestBody UserDto userDto){

        Response response = userService.sendOtp(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<?> verifyOtp(@RequestBody UserDto userDto){
        Response response = userService.verifyOtp(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UserDto userDto){

        Response response = userService.updatePassword(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto){
            Response result = userService.deleteUser(userDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


