package com.example.openfeign.summoner.infra.riotDto;

public record InfoDto(
        int attack,
        int defense,
        int magic,
        int difficulty
) {
}
