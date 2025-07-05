package com.evantagesoft.ecommerce.service.user;

import com.evantagesoft.ecommerce.dto.UserDto;
import com.evantagesoft.ecommerce.entity.User;
import com.evantagesoft.ecommerce.repository.UserRepository;
import com.evantagesoft.ecommerce.response.EcommResponse;
import com.evantagesoft.ecommerce.response.Response;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Response registerUser(UserDto userDto)  {

        Response response = new Response();

       try {
           // Check if the user already exists
           User existingUser = userRepository.findByEmail(userDto.getEmail());
           if (existingUser != null){
               response.setCode("404");
               response.setMessage("User Already Exist With This Email");
               return response;
           }


           if (userDto.getUsername() == null || userDto.getUsername().isEmpty()){
               response.setCode("400");
               response.setMessage("Username Cannot be null or Empty");
               return response;
           }
           if (userDto.getEmail() == null || userDto.getEmail().isEmpty()){
               response.setCode("400");
               response.setMessage("Email cannot be null or Empty");
               return response;
           }

           User save = userRepository.save(toEntity(userDto));
           response.setResponse(EcommResponse.USER_REGISTERED_SUCCESSFULLY);
           response.setData("data", save);
           return response;
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return response;
    }

    @Override
    public Response loginUser(UserDto userDto) {

        Response response = new Response();
        try {
            if (userDto == null){
                response.setResponse(EcommResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }

            User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
            if (user == null){
                response.setResponse(EcommResponse.INVALID_CREDENTIALS);
                return response;
            }

            else {
                response.setResponse(EcommResponse.LOGIN_SUCCESSFUL);
                return response;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }


    @Override
    public Response verifyEmail(UserDto userDto) {

        Response response = new Response();
        try {
            if (userDto == null){
                response.setResponse(EcommResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }

            User optionalUser = userRepository.findByEmail(userDto.getEmail());
            if (optionalUser == null){
                response.setResponse(EcommResponse.USER_NOT_FOUND);
                return response;
            }

            else {
                response.setCode("200");
                response.setMessage("Email is verified");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response sendOtp(UserDto userDto) {

        Response response = new Response();
        User user = null;
        try {
            if (userDto == null){
                response.setResponse(EcommResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }

             user  = userRepository.findByEmail(userDto.getEmail());
            if (user == null){
                response.setResponse(EcommResponse.USER_NOT_FOUND);
                return response;
            }

            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);

            String subject = "Your OTP for email verification";
            String text = "Your OTP is: " +  otp;

            sendEmail(subject,text,userDto.getEmail());


            user.setOtp(otp);
            user = userRepository.save(user);

            response.setResponse(EcommResponse.OTP_SEND_SUCCESSFULLY);
            response.setData("data", user);
            return response;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response verifyOtp(UserDto userDto) {

        Response response = new Response();
        try {
            if (userDto == null){
                response.setResponse(EcommResponse.INVALID_REQUEST_PARAMETER);
                return response;
            }
            User user = userRepository.findByEmail(userDto.getEmail());
            Integer otpValue = user.getOtp();

            if (otpValue.equals(userDto.getOtp())){
                response.setCode("200");
                response.setMessage("OTP is verified");
                return response;
            }

            else {
                response.setCode("400");
                response.setMessage("OTP does not match");
                return response;
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updatePassword(UserDto userDto) {
        Response response = new Response();
        try {
            User user = userRepository.findByEmail(userDto.getEmail());
            if (user == null){
                response.setResponse(EcommResponse.USER_NOT_FOUND);
                return response;
            }
            if (userDto.getOldPassword().equals(user.getPassword())){
                user.setPassword(userDto.getPassword());
            }
            else {
                response.setCode("404");
                response.setMessage("Old Password Does Not Match");
                return response;
            }

            User save = userRepository.save(user);

            response.setResponse(EcommResponse.PASSWORD_UPDATED_SUCCESSFULLY);
            response.setData("data", save);
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteUser(UserDto userDto) {

        Response response = new Response();

        try {

            User user = userRepository.findByEmail(userDto.getEmail());
            if (user == null){
                response.setResponse(EcommResponse.USER_NOT_FOUND);
                return response;
            }

            user.setIsActive(false);
            response.setCode("200");
            response.setMessage("User Deleted Successfully");
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }


    private void sendEmail(String subject, String text, String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }


    public User toEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
