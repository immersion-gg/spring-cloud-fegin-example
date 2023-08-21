package com.example.openfeign.summoner.match;

public record ParticipantsQueryResponse(
        int assists,
        int champLevel,
        int championId,
        String championName,
        int deaths,
        String individualPosition,
        int item0,
        int item1,
        int item2,
        int item3,
        int item4,
        int item5,
        int item6,
        int kills,
        int participantId,
        String puuid,
        int summonerLevel,
        String summonerName,
        int teamId,
        String teamPosition,
        int totalDamageDealtToChampions,
        int totalDamageTaken,
        int wardPlaced,
        boolean win
) {
}
