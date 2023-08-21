package com.example.openfeign.summoner.match;

public record TeamQueryResponse(
        int teamId,
        boolean win
) {
}
