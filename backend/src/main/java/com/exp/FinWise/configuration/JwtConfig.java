package com.exp.FinWise.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class JwtConfig {


    private String jwtSecret= "=======================jwtfinwiseAPPlicationSecrete=Spring===========================";


    private long jwtExpirationMs=86400000;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public long getJwtExpirationMs() {
        return jwtExpirationMs;
    }
}
