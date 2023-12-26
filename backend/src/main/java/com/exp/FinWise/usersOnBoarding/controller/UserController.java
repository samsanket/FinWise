package com.exp.FinWise.usersOnBoarding.controller;

import com.exp.FinWise.annotation.CurrentUser;
import com.exp.FinWise.response.ResponseResume;
import com.exp.FinWise.usersOnBoarding.dto.UpdateUserProfileDto;
import com.exp.FinWise.usersOnBoarding.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserProfileService userProfileService;
    @GetMapping("/profile")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> getUserProfile(@CurrentUser UserDetails currentUser) {
        return ResponseEntity.ok(currentUser.getUsername());
    }
    @PostMapping("update/profile")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseResume updateUserProfile(@CurrentUser UserDetails currentUser, @RequestBody UpdateUserProfileDto updateUserProfileDto) {
        return userProfileService.updateUserProfile(currentUser, updateUserProfileDto);
    }

    @GetMapping("/user/profile")
    @CrossOrigin(origins = "http://localhost:3000")
    public UpdateUserProfileDto getCurrent(@CurrentUser UserDetails currentUser) {
        return userProfileService.getCompleteUserProfile(currentUser.getUsername());
    }
}
