package com.example.openfeign.summoner.infra;

import com.example.openfeign.config.RiotFeignConfiguration;
import com.example.openfeign.summoner.infra.riotDto.SummonerQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "summoner", configuration = RiotFeignConfiguration.class, url = "${riot.api.url}")
public interface SummonerClient {
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    Optional<SummonerQueryResponse> getSummoner(@PathVariable String summonerName);
}
