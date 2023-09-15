package com.example.api_times.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMap mapper(){
        return new ModelMap();
    }
}