package com.example.openfeign.summoner.infra;

import java.util.List;

public record LeagueListQueryResponse(
        String leagueId,
        List<LeagueItemQueryResponse> entries,
        String tier,
        String name,
        String queue
) {
}
