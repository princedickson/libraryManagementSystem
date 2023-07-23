package com.explicit.libraryManagementSystem.Web.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{

    @Bean
    public String myKey() {
        return "your_secret_key";
    }
}