package com.example.openfeign.summoner.infra.riotDto;

import java.util.List;

public record Perks(
        List<String> perkIds,
        String perkStyle,
        String perkSubStyle
) {
}
