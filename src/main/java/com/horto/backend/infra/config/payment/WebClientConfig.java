package com.horto.backend.infra.config.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${spring.mercadopago.access.token}")
    private String accessToken;

    @Bean
    public WebClient mercadoPagoClient() {
        return WebClient.builder()
                .baseUrl("https://api.mercadopago.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build();
    }
}