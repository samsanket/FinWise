package com.exp.FinWise.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class JwtConfig {


    @Value("${finwise.app.jwtSecret}")
    private String jwtSecret;

    @Value("${finwise.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public long getJwtExpirationMs() {
        return jwtExpirationMs;
    }
}
