package com.exp.FinWise.usersOnBoarding.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.exp.FinWise.audit.service.AuditService;
import com.exp.FinWise.response.JwtResponse;
import com.exp.FinWise.response.MessageResponse;
import com.exp.FinWise.security.jwt.JwtUtils;
import com.exp.FinWise.usersOnBoarding.dto.LoginRequest;
import com.exp.FinWise.usersOnBoarding.dto.SignupRequest;
import com.exp.FinWise.usersOnBoarding.model.ERole;
import com.exp.FinWise.usersOnBoarding.model.Role;
import com.exp.FinWise.usersOnBoarding.model.User;
import com.exp.FinWise.usersOnBoarding.repository.RoleRepository;
import com.exp.FinWise.usersOnBoarding.repository.UserRepository;
import com.exp.FinWise.usersOnBoarding.service.UserDetailsImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  AuditService auditService;
  public void saveAudit(String apiname,String data){
    auditService.saveAudit(apiname,data);
  }
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
    saveAudit("/api/auth/signin","login a user with userName  "+loginRequest.getUsername());
    if (StringUtils.isBlank(loginRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username should not be blank"));
    }

    // Check if the password is blank
    if (StringUtils.isBlank(loginRequest.getPassword())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Password should not be blank"));
    }
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
    saveAudit("/api/auth/signin","logged in  success "+userDetails.getUsername());
    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
    saveAudit("/api/auth/signup","register a user with user name "+ signUpRequest.getUsername());

    if (StringUtils.isBlank(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username should not be blank"));
    }

    // Check if the email is blank
    if (StringUtils.isBlank(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email should not be blank"));
    }

    // Check if the role is blank or empty
    if (signUpRequest.getRole() == null || signUpRequest.getRole().isEmpty()) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Role should not be blank or empty"));
    }

    // Check if the password is blank
    if (StringUtils.isBlank(signUpRequest.getPassword())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Password should not be blank"));
    }

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "ROLE_ADMIN":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "ROLE_MODERATOR":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    saveAudit("/api/auth/signin","account created success "+user.getUsername());

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}