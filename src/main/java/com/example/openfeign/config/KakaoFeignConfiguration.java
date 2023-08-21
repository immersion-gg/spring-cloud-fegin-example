package com.example.openfeign.config;

import com.example.openfeign.summoner.infra.client.LocalSearchClient;
import feign.Logger;
import feign.RequestInterceptor;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

public class KakaoFeignConfiguration {
    private final String apiKey;

    public KakaoFeignConfiguration(@Value("${kakao.api.key}") String apiKey) {
        if (!StringUtils.startsWithIgnoreCase(apiKey, "KakaoAK")) {
            throw new PropertyNotFoundException("API키를 추가해주세용");
        }

        this.apiKey = apiKey;
    }

    @Bean
    Logger.Level kakaoFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    RequestInterceptor kakaoRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", apiKey);
    }
}
