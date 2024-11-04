package com.evantagesoft.ecommerce.service.user;

import com.evantagesoft.ecommerce.dto.UserDto;
import com.evantagesoft.ecommerce.entity.User;
import com.evantagesoft.ecommerce.repository.UserRepository;
import com.evantagesoft.ecommerce.response.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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

        User user = null;

        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()){
            return new Response("402","User With email " + userDto.getEmail() + " already exist.");
        }

            if (userDto.getUsername() == null || userDto.getUsername().isEmpty()){
                return new Response("400","Username Cannot be null or Empty");
            }
            if (userDto.getEmail() == null || userDto.getEmail().isEmpty()){
                return new Response("401","Email cannot be null or Empty");
            }


            user = userRepository.save(toEntity(userDto));

            return new Response("200","User Registered Successfully");
    }

    @Override
    public Response loginUser(UserDto userDto) throws Exception {

//        User user = null;

            if (userDto.getEmail() == null || userDto.getEmail().isEmpty()){
                throw new Exception("Email cannot be null or Empty ");
            }
            if (userDto.getPassword() == null || userDto.getPassword().isEmpty()){
                throw new Exception("Password cannot be null or Empty");
            }

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

            if (!optionalUser.isPresent()) {
                return new Response("400", "User not Found");
            }

        User user = optionalUser.get();

            if (user.getPassword().equalsIgnoreCase(userDto.getPassword())){
                return new Response("200","login Successfull");
            }else {
                return new Response("401","Invalid Password");
            }

    }


    @Override
    public Response verifyEmail(String email) {
//        User user = null;

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()){
            return new Response("400", "User not Found");
        }

        User user = optionalUser.get();
        if (user.getEmail().equals(email)) {
            return new Response("200", "User is Verified");
        }
        else {
            return new Response("401", "Email does not match");
        }
    }

    @Override
    public Response sendOtp(String email) {
//        User user = null;

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        String subject = "Your OTP for email verification";
        String text = "Your OTP is: " +  otp;

        sendEmail(subject,text,email);

        Optional<User> optionalUser  = userRepository.findByEmail(email);

        if (optionalUser != null){
           User user = optionalUser.get();
           user.setOtp(otp);
            userRepository.save(user);
            return new Response("200", "OTP sent successfully");
        }
        else {
            return new Response("400", "User not found");
        }

    }

    @Override
    public Response verifyOtp(String email, int otp) {
//        User user = null;

        Optional<User> optionalUser= userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            int otpValue = user.getOtp();

            if (otp == otpValue) {
                return new Response("200", "OTP is Verified");
            } else {
                return new Response("400", "OTP does not match");
            }
        }
          return new Response("404", "User not found");
    }

    @Override
    public Response updatePassword(UserDto userDto) {
        User user = null;

        try {
            Optional<User> users = userRepository.findByEmail(userDto.getEmail());

           user.setPassword(userDto.getPassword());

           userRepository.save(user);

           return new Response("200", "Password updated Successfully");
        }
        catch (Exception e){
            return new Response("400","Failed to update Password");
        }

    }

    @Override
    public String deleteUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){

            User user = optionalUser.get();
            userRepository.delete(user);

            return ("User Deleted Successfully " + user.getEmail());
        }
        else {
            return ("User not Found");
        }

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
