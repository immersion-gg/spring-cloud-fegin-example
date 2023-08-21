package com.example.openfeign.summoner.infra.riotDto;

public record ParticipantDto(
        String championId,
        Perks perks,
        String profileIconId,
        String teamId,
        String summonerName,
        String summonerId,
        String spell1Id,
        String spell2Id
) {
}
