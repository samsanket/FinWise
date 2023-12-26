package com.exp.FinWise.usersOnBoarding.service;

import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.response.ResponseResume;
import com.exp.FinWise.usersOnBoarding.dto.UserSinginRquest;
import com.exp.FinWise.usersOnBoarding.model.User;
import com.exp.FinWise.usersOnBoarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserOnBoardingService {

    @Autowired
    ResponseSBuilder responseSBuilder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseResume singin(UserSinginRquest userSinginRquest) {
        User user= new User();
        user.setAuthenticated(false);
        user.setEmail(userSinginRquest.getEmail());
        user.setPassword(userSinginRquest.getPassword());
        user.setUsername(userSinginRquest.getUserName());
        userRepository.save(user);
        // TO-DO
        // send verify email link over the mail
        // or enter otp to verify the user

    return responseSBuilder.createResponse("User signed in successfully");
    }

    public ResponseResume singup(UserSinginRquest userSinginRquest) {


        return responseSBuilder.createResponse("User signed in successfully");
    }


}
