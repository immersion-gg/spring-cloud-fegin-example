package com.example.openfeign.summoner.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@EnableFeignClients(clients = {SummonersByLeague.class, SummonerClient.class})
class SummonersByLeagueTest {

    @Autowired
    private SummonersByLeague summonersByLeague;
    @Autowired
    private SummonerClient summonerClient;

    @Test
    @DisplayName("입력한 티어에 해당하는 유저들 정보 출력")
    void 리그정보입력() {
        String queue = "RANKED_SOLO_5x5";
        String tier = "GOLD";
        String division = "III";

        Set<LeagueEntryQueryResponse> responseOpt = summonersByLeague.getLeagueSummoners(queue, tier, division);

        assertThat(responseOpt).isNotNull();
    }

    @Test
    @DisplayName("입력한 티어에 해당하는 유저들 정보 출력")
    void FLEX_TT() {
        String queue = "RANKED_FLEX_TT";
        String tier = "GOLD";
        String division = "III";

        Set<LeagueEntryQueryResponse> responseOpt = summonersByLeague.getLeagueSummoners(queue, tier, division);

        assertThat(responseOpt).isEmpty();
    }

    @Test
    @DisplayName("입력한 티어에 해당하는 유저들 정보 출력")
    void BySummonerName() {
        String givenSummonerName = "김석균";

        Optional<SummonerQueryResponse> responseOpt = summonerClient.getSummoner(givenSummonerName);

        responseOpt.ifPresentOrElse(
                it -> assertThat(it.name()).isEqualTo(givenSummonerName),
                () -> fail("소환사명이 일치하지 않음")
        );

        Set<LeagueEntryQueryResponse> responseOpts = summonersByLeague.getLeagueSummoners(responseOpt.get().id());

        assertThat(responseOpt).isNotNull();
    }

    @Test
    void LeagueByLeagueId() {
        String leagueId = "966df365-7dea-34ca-839a-2cec050ba64a";

        Optional<LeagueListQueryResponse> responseOpt = summonersByLeague.getLeagueInfo(leagueId);

        responseOpt.ifPresentOrElse(
                it -> assertThat(it.leagueId()).isEqualTo(leagueId),
                () -> fail("소환사명이 일치하지 않음")
        );

        assertThat(responseOpt).isNotNull();
    }
}