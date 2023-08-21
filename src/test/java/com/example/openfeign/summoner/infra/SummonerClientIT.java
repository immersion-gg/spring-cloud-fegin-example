package com.example.openfeign.summoner.infra;

import com.example.openfeign.config.RiotFeignConfiguration;
import com.example.openfeign.summoner.infra.client.RiotClient;
import com.example.openfeign.summoner.infra.riotDto.CurrentGameQueryResponse;
import com.example.openfeign.summoner.infra.riotDto.SummonerQueryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class SummonerClientIT {

    @Autowired
    private RiotClient riotClient;

    @Test
    @DisplayName("소환사 이름으로 소환사 정보 가져오기")
    void When_GetSummerInfo() {
        String givenSummonerName = "김석균";

        Optional<SummonerQueryResponse> responseOpt = riotClient.getSummoner(givenSummonerName);

        responseOpt.ifPresentOrElse(
                it -> assertThat(it.name()).isEqualTo(givenSummonerName),
                () -> fail("소환사명이 일치하지 않음")
        );
    }


    @Test
    public void givenSummonerId_whenInvokeCurrentGameInfo_thenReturnCurrentGameInfo() throws Exception {

        //Given
        String encryptedSummonerId = "Ugd3BRlX6bqbPF2E-Mdarzz1vUkpKvWr905XP6hHxB6ImbE";
        //When

        Optional<CurrentGameQueryResponse> responseOpt = riotClient.getCurrentGameInfo(encryptedSummonerId);
        //Then
        responseOpt.ifPresentOrElse(
                it -> assertThat(it.gameId()).isNotEmpty(),
                () -> fail("현재 게임중이 아닙니다.")
        );

        System.out.println("responseOpt = " + responseOpt);
    }

    @Test
    @DisplayName("소환사 이름으로 소환사 정보 가져오기")
    void givenSummonerName_WhenInvokeCurrentGameInfo_thenReturnCurrentGameQueryResponse() {
        String givenSummonerName = "쪼잔하게 굴지마";

        Optional<SummonerQueryResponse> responseOpt = riotClient.getSummoner(givenSummonerName);

        responseOpt.ifPresentOrElse(
                it -> assertThat(riotClient.getCurrentGameInfo(it.id())).isPresent(),
                () -> fail("현재 게임중이 아닙니다.")
        );
    }


    @Test
    @DisplayName("존재하지 않는 소환사명으로 정보를 가져오려 했을 때")
    void Given_NotExistsSummonerName_Then_ReturnOptionalEmpty() {
        String givenNotExistsSummonerName = "김석균1234asweewq";

        Optional<SummonerQueryResponse> responseOpt = riotClient.getSummoner(givenNotExistsSummonerName);

        assertThat(responseOpt).isEmpty();
    }
}