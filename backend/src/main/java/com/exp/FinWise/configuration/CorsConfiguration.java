//package com.vidya.leap.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration implements WebMvcConfigurer {
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("http://localhost:3000") // Allow all origins, you can specify specific origins if needed
//            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
//            .allowedHeaders("Access-Control-Allow-Origin") // Allow all headers
//            .allowCredentials(true) // Allow credentials like cookies, authentication, etc.
//            .maxAge(3600); // Set the maximum age of the CORS preflight request
//    }
//}