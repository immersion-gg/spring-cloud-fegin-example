package com.example.openfeign.summoner.infra.riotDto;

import java.util.Map;

public record DataDto(
        String type,
        String format,
        String version,
        Map<String, ChampionDto> data
) {
}
