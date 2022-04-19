package com.example.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.example.countries.*" })
public class CountriesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }
}
