package com.shop.in.BeanConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public String message1() {
        return "Hello World";
    }

    @Bean
    public String message2(){
        return "kem cho dunia";
    }

}
