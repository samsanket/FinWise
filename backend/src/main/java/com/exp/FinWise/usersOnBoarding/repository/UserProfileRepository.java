package com.exp.FinWise.usersOnBoarding.repository;

import com.exp.FinWise.usersOnBoarding.model.User;
import com.exp.FinWise.usersOnBoarding.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile , Long> {

//    Optional<UserProfile> findByUsername(String username);

    Optional<UserProfile> findByUser(User username);
}
