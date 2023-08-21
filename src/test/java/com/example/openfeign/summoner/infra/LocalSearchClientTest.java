package com.example.openfeign.summoner.infra;

import com.example.openfeign.config.KakaoFeignConfiguration;
import com.example.openfeign.summoner.infra.client.LocalSearchClient;
import com.example.openfeign.summoner.infra.kakaoDto.LocalSearchQueryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(KakaoFeignConfiguration.class)
class LocalSearchClientTest {

    @Autowired
    LocalSearchClient localSearchClient;

    @Test
    @DisplayName("카카오 API 테스트")
    public void given_when_then() throws Exception {

        //Given
        String address = "충북 청주시";
        //When
        LocalSearchQueryResponse res = localSearchClient.getAddress(address);
        //Then
        System.out.println("res = " + res.documentDtoList().get(0).addressName());


    }

}