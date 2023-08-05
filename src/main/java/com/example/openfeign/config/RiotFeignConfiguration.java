package com.example.openfeign.config;

import feign.Logger;
import feign.RequestInterceptor;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableFeignClients
public class RiotFeignConfiguration {
    private final String apiKey;

    public RiotFeignConfiguration(@Value("${riot.api.key}") String apiKey) {
        if (!StringUtils.startsWithIgnoreCase(apiKey, "RGAPI")) {
            throw new PropertyNotFoundException("API키를 추가해주세용");
        }

        this.apiKey = apiKey;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    RequestInterceptor riotRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("X-Riot-Token", apiKey);
    }
}
