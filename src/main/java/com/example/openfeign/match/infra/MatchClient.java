package com.example.openfeign.match.infra;

import com.example.openfeign.config.RiotFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "match", configuration = RiotFeignConfiguration.class, url = "${riot.api.AsiaUrl}")

public interface MatchClient{
    @GetMapping("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    List<String> getMatchIdsByPUUID(@RequestParam String puuid);
}
