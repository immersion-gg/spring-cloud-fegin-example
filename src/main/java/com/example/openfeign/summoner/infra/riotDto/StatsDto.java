package com.example.openfeign.summoner.infra.riotDto;

public record StatsDto(
        int hp,
        int hpperlevel,
        int mp,
        int mpperlevel,
        int movespeed,
        int armor,
        double armorperlevel,
        int spellblock,
        double spellblockperlevel,
        int attackrange,
        double hpregen,
        double hpregenperlevel,
        double mpregen,
        double mpregenperlevel,
        int crit,
        int critperlevel,
        int attackdamage,
        double attackdamageperlevel,
        double attackspeedperlevel,
        double attackspeed
) {
}
