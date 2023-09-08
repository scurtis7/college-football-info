package com.scurtis.server.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.util.Assert.hasText;

@Data
@Configuration
@ConfigurationProperties(prefix = "cfb")
public class CfbConfig {

    private String baseUrl;
    private String apiKey;

    @PostConstruct
    public void postConstruct() {
        hasText(baseUrl, "cfb.base-url is a required property");
        hasText(apiKey, "cfb.api-key is a required property");
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}
