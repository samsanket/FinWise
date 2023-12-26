package com.exp.FinWise.usersOnBoarding.service;

import com.exp.FinWise.response.ResponseSBuilder;
import com.exp.FinWise.response.ResponseResume;
import com.exp.FinWise.usersOnBoarding.dto.UpdateUserProfileDto;
import com.exp.FinWise.usersOnBoarding.model.User;
import com.exp.FinWise.usersOnBoarding.model.UserProfile;
import com.exp.FinWise.usersOnBoarding.repository.UserProfileRepository;
import com.exp.FinWise.usersOnBoarding.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {
    Logger log = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    ResponseSBuilder responseSBuilder;
    public ResponseResume updateUserProfile(UserDetails currentUser, UpdateUserProfileDto updateUserProfileDto) {
        Optional<User> optionalUseruser= userRepository.findByUsername(currentUser.getUsername());
        User  user = null;
        if (optionalUseruser.isPresent()){
            user=optionalUseruser.get();
        }

        Optional<UserProfile>   optionalUserProfile= userProfileRepository.findByUser(user);


        if (optionalUserProfile.isPresent()){
            UserProfile userProfile = getUserProfile(updateUserProfileDto, optionalUserProfile);
            userProfileRepository.save(userProfile);
        }else {
            UserProfile userProfile = new UserProfile(
                    updateUserProfileDto.getAddress(),
                    updateUserProfileDto.getDateOfBirth()
                    , updateUserProfileDto.getEducation(),
                    updateUserProfileDto.getCollegeName()
                    , updateUserProfileDto.getPercentage(),
                    updateUserProfileDto.getBranch(), user
            );
            userProfileRepository.save(userProfile);
        }



        return responseSBuilder.createResponse(user);
    }

    private static UserProfile getUserProfile(UpdateUserProfileDto updateUserProfileDto, Optional<UserProfile> optionalUserProfile) {
        UserProfile userProfile = optionalUserProfile.get();
        userProfile.setAddress(updateUserProfileDto.getAddress());
        userProfile.setDateOfBirth(updateUserProfileDto.getDateOfBirth());
        userProfile.setEducation(updateUserProfileDto.getEducation());
        userProfile.setCollegeName(updateUserProfileDto.getCollegeName());
        userProfile.setPercentage(updateUserProfileDto.getPercentage());
        userProfile.setBranch(updateUserProfileDto.getBranch());
        return userProfile;
    }

    public UpdateUserProfileDto getCompleteUserProfile(String username) {
        Optional<User> optionalUseruser= userRepository.findByUsername(username);
        User  user = null;
        log.info("in get methond");
        if (optionalUseruser.isPresent()){
            user=optionalUseruser.get();
            log.info("User found");
        }

        Optional<UserProfile>   optionalUserProfile= userProfileRepository.findByUser(user);
        UserProfile userProfile = null;

        if (optionalUserProfile.isPresent()){
                userProfile=optionalUserProfile.get();
                log.info("User Profile found");
            return  new UpdateUserProfileDto(
                    userProfile.getAddress(),
                    userProfile.getDateOfBirth(),
                    userProfile.getEducation(),
                    userProfile.getCollegeName(),
                    userProfile.getPercentage(),
                    userProfile.getBranch());
        }

return new UpdateUserProfileDto("","",
        "",""," ","");




    }
}
