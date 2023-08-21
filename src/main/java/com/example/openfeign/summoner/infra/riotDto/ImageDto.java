package com.example.openfeign.summoner.infra.riotDto;

public record ImageDto(
        String full,
        String sprite,
        String group,
        int x,
        int y,
        int w,
        int h
) {
}
