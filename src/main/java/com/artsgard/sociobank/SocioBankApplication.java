package com.artsgard.sociobank;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SocioBankApplication implements CommandLineRunner {

    org.slf4j.Logger logger = LoggerFactory.getLogger(SocioBankApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SocioBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
