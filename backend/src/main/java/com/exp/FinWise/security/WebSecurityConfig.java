package com.exp.FinWise.security;

import com.exp.FinWise.security.jwt.AuthEntryPointJwt;
import com.exp.FinWise.security.jwt.AuthTokenFilter;
import com.exp.FinWise.usersOnBoarding.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig  {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }
  
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }

  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf ->csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
          auth.requestMatchers("/api/auth/**","/api/auth/signup","/openai/**","/swagger-ui/**","/swagger-ui/index.html","/api-docs/swagger-config/**","/api-docs/**","/api-docs",
                          "/api/v1/resume/save","/auditevents","/actuator/**","api/v1/py/scrape/save/","/api/v1/resume/otp/received/{requirementId}"," /resumes/file/**","/files/**"
                  ,"/upload", "/files", "/files/**", "/files/url/**","/api/v1/audit/**")
                  .permitAll()
              .requestMatchers("/api/test/**").permitAll()
              .anyRequest().authenticated()
        );
    http.cors();
    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  private static AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizedUrl getRequestedMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
    return auth.requestMatchers(
            "/api/auth/**",
            "/api/auth/signup",
            "/openai/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/api-docs/swagger-config/**",
            "/api-docs/**",
            "/api-docs",
            "/api/v1/resumek/save",
            "/auditevents",
            "/actuator/**",
            "api/v1/py/scrape/save/",
            "/api/v1/resume/otp/received/{requirementId}",
            " /resumes/file/**",
            "/files/**"
            , "/upload",
            "/files",
            "/files/**",
            "/files/url/**",
            "/api/v1/audit",
            "/api/v1/audit"
    );
  }

}
