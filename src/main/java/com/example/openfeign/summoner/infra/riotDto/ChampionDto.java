package com.example.openfeign.summoner.infra.riotDto;


public record ChampionDto(
        String version,
        String id,
        String key,
        String name,
        String title,
        String blurb,
        InfoDto info,
        ImageDto image,
        String[] tags,
        String partype,
        StatsDto stats
) {
}
