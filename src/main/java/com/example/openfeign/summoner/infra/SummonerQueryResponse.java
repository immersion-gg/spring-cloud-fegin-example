package com.example.openfeign.summoner.infra;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record SummonerQueryResponse(
        String id,
        String accountId,
        String puuid,
        String name,
        String profileIconId,
        Timestamp revisionDate,
        String summonerLevel
) {

    public LocalDateTime getRevisionDate() {
        return revisionDate.toLocalDateTime();
    }
}
