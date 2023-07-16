package com.example.restapidemo;

import com.example.restapidemo.unit.rest.security.interceptors.RestAccessInterceptor;
import com.example.restapidemo.unit.service.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration implements WebMvcConfigurer {

    @Autowired
    RequestService requestService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestAccessInterceptor(requestService));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
