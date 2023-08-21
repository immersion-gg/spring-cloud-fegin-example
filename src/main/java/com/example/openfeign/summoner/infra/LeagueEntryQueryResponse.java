package com.example.openfeign.summoner.infra;

public record LeagueEntryQueryResponse(
        String leagueId,
        String summonerId,
        String SummonerName,
        String queueType,
        String tier,
        String rank,
        int leaguePoints,
        int wins,
        int loses,
        boolean hotStreak,
        boolean veteran,
        boolean freshBlood,
        boolean inactive
) {

}

