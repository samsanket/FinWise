package com.exp.FinWise.usersOnBoarding.controller;

import com.exp.FinWise.response.ResponseCommon;
import com.exp.FinWise.usersOnBoarding.dto.UserSinginRquest;
import com.exp.FinWise.usersOnBoarding.service.UserOnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserOnBoardingController {

    @Autowired
    UserOnBoardingService userOnBoardingService;

    @PostMapping("/singin")
    public ResponseCommon singin(@RequestBody UserSinginRquest userSinginRquest) {
        return userOnBoardingService.singin(userSinginRquest);
    }

    @PostMapping("/singup")
    public ResponseCommon singup(@RequestBody UserSinginRquest userSinginRquest) {
        return userOnBoardingService.singup(userSinginRquest);
    }
}

