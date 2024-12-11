package com.example.EmpRoleManager.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "http://localhost:2001/stock"; // Replace with actual Stock Service URL

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().baseUrl(BASE_URL);
    }
}
