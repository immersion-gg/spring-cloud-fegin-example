package com.example.openfeign.match.infra;

import com.example.openfeign.summoner.infra.SummonerClient;
import com.example.openfeign.summoner.infra.SummonerQueryResponse;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@EnableFeignClients(clients = SummonerClient.class)
@WireMockTest(httpPort = 8080)
@TestPropertySource(properties = {
        "riot.api.url=http://localhost:8080",
        "riot.api.key=RGAPI_APIKEY"
})
class WireMockMatchClientTest {
    @Autowired
    private MatchClient matchClient;

    @Test
    @DisplayName("존재하지 않는 Puuid로 정보를 가져오려 했을 때")
    void given_NotExistsPuuid_Then_ReturnEmptyList() {
        String givenNotExistsPuuid = "김석균1234asweewq";

        stubFor(get(urlEqualTo("//lol/match/v5/matches/by-puuid/" + givenNotExistsPuuid +"/ids"))
                .withHeader("X-Riot-Token", equalTo("RGAPI_APIKEY"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {
                                  "status": {
                                    "message": "Data not found - puuid not found",
                                    "status_code": 404
                                  }
                                }
                                  """)));
        List<String> responseOpt = matchClient.getMatchIdsByPUUID(givenNotExistsPuuid);


        assertThat(responseOpt).isEmpty();
    }

}