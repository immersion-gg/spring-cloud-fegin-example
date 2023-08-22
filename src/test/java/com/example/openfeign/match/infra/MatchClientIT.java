package com.example.openfeign.match.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@SpringBootTest
class MatchClientIT {

    @Autowired
    private MatchClient matchClient;


    @Test
    @DisplayName("puuid로 Match ID 가져오기")
    void when_GetMatchIdsByPUUID() {
        String puuid = "Xyobxwc7L-cIFKzEu9HeYK0RMw9EKdSHNa7FJMjx2qxhYGopJchgiGuocwSH3ga4Tg7A-svEiGS5nw";

        List<String> responseOpt = matchClient.getMatchIdsByPUUID(puuid);

    }

    @Test
    @DisplayName("존재하지 않는 Puuid로 정보를 가져오려 했을 때")
    void given_NotExistsPuuid_Then_ReturnEmptyList() {
        String givenNotExistsPuuid = "nonExistentPuuid";

        List<String> responseOpt = matchClient.getMatchIdsByPUUID(givenNotExistsPuuid);

        assertThat(responseOpt).isEmpty();
    }
}
