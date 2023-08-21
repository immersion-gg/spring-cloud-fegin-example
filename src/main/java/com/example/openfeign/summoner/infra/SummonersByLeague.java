package com.example.openfeign.summoner.infra;

import com.example.openfeign.config.RiotFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.Set;

@FeignClient(name = "league", configuration = RiotFeignConfiguration.class, url = "${riot.api.url}")
public interface SummonersByLeague {
    @GetMapping("/lol/league/v4/entries/{queue}/{tier}/{division}")
    Set<LeagueEntryQueryResponse> getLeagueSummoners(@PathVariable String queue, @PathVariable String tier, @PathVariable String division);

    @GetMapping("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    Set<LeagueEntryQueryResponse> getLeagueSummoners(@PathVariable String encryptedSummonerId);

    @GetMapping("/lol/league/v4/leagues/{leagueId}")
    Optional<LeagueListQueryResponse> getLeagueInfo(@PathVariable String leagueId);
}
