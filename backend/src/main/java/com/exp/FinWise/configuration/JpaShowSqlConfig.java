package com.exp.FinWise.configuration;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
public class JpaShowSqlConfig {

    private final JpaProperties jpaProperties;

    public JpaShowSqlConfig(JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
        this.jpaProperties.setShowSql(true);

    }
}