package com.example.openfeign.summoner.infra;

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
class WireMockSummonerClientTest {
    @Autowired
    private SummonerClient summonerClient;

    @Test
    @DisplayName("존재하지 않는 소환사명으로 정보를 가져오려 했을 때")
    void Given_NotExistsSummonerName_Then_ReturnOptionalEmpty() {
        String givenNotExistsSummonerName = "김석균1234asweewq";
        String encodedSummonerName = givenNotExistsSummonerName.transform(encodingUTF_8());

        stubFor(get(urlEqualTo("/lol/summoner/v4/summoners/by-name/" + encodedSummonerName))
                .withHeader("X-Riot-Token", equalTo("RGAPI_APIKEY"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {
                                  "status": {
                                    "message": "Data not found - summoner not found",
                                    "status_code": 404
                                  }
                                }
                                  """)));

        Optional<SummonerQueryResponse> responseOpt = summonerClient.getSummoner(givenNotExistsSummonerName);

        assertThat(responseOpt).isEmpty();
    }

    @Test
    @DisplayName("유효하지 않은 토큰으로 API 호출시 Exception")
    void Given_OverdueToken_Then_ThrowException() {
        String givenAnySummonerName = "아무이름";
        String encodedSummonerName = givenAnySummonerName.transform(encodingUTF_8());

        stubFor(get(urlEqualTo("/lol/summoner/v4/summoners/by-name/" + encodedSummonerName))
                .withHeader("X-Riot-Token", equalTo("RGAPI_APIKEY"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.FORBIDDEN.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                  {
                                      "status": {
                                          "message": "Forbidden",
                                          "status_code": 403
                                      }
                                  }
                                  """)));

        assertThrows(FeignException.Forbidden.class , ()-> summonerClient.getSummoner(givenAnySummonerName));
    }

    private Function<String, String> encodingUTF_8() {
        return s -> URLEncoder.encode(s, StandardCharsets.UTF_8);
    }
}