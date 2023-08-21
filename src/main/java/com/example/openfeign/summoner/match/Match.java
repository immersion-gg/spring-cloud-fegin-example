package com.example.openfeign.summoner.match;

import com.example.openfeign.config.RiotFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "match", configuration = RiotFeignConfiguration.class, url = "${riot.api.url}")
public interface Match {
    @GetMapping("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    List<String> getSummoner(@PathVariable String puuid);

    @GetMapping("/lol/match/v5/matches/{matchId}")
    MatchQueryResponse getMatch(@PathVariable String matchId);
}