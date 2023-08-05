package com.example.openfeign.summoner.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@EnableFeignClients(clients = SummonerClient.class)
class SummonerClientIT {

    @Autowired
    private SummonerClient summonerClient;

    @Test
    @DisplayName("소환사 이름으로 소환사 정보 가져오기")
    void When_GetSummerInfo() {
        String givenSummonerName = "김석균";

        Optional<SummonerQueryResponse> responseOpt = summonerClient.getSummoner(givenSummonerName);

        responseOpt.ifPresentOrElse(
                it -> assertThat(it.name()).isEqualTo(givenSummonerName),
                () -> fail("소환사명이 일치하지 않음")
        );
    }

    @Test
    @DisplayName("존재하지 않는 소환사명으로 정보를 가져오려 했을 때")
    void Given_NotExistsSummonerName_Then_ReturnOptionalEmpty() {
        String givenNotExistsSummonerName = "김석균1234asweewq";

        Optional<SummonerQueryResponse> responseOpt = summonerClient.getSummoner(givenNotExistsSummonerName);

        assertThat(responseOpt).isEmpty();
    }
}