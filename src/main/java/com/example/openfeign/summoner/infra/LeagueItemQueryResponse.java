package com.example.openfeign.summoner.infra;

public record LeagueItemQueryResponse(
        boolean freshBlood,
        int wins,
        String summonerName,
        boolean inactive,
        boolean veteran,
        boolean hotStreak,
        String rank,
        int leaguePoints,
        int losses,
        String SummonerId
) {
}
