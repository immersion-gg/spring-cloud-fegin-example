package com.example.openfeign.summoner.infra.client;

import com.example.openfeign.config.RiotFeignConfiguration;
import com.example.openfeign.summoner.infra.riotDto.CurrentGameQueryResponse;
import com.example.openfeign.summoner.infra.riotDto.SummonerQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "Riot", configuration = {RiotFeignConfiguration.class}, url = "${riot.api.url}")
public interface RiotClient {
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    Optional<SummonerQueryResponse> getSummoner(@PathVariable String summonerName);

    @GetMapping("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    Optional<CurrentGameQueryResponse> getCurrentGameInfo(@PathVariable String encryptedSummonerId);
}
