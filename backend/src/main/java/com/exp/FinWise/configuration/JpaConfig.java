package com.exp.FinWise.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.exp.FinWise")
@EnableTransactionManagement
public class JpaConfig {


    public JpaConfig() {
        System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
    }
}