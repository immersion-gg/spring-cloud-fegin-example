package com.example.openfeign.summoner.infra.riotDto;

import com.example.openfeign.summoner.infra.riotDto.ParticipantDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public record CurrentGameQueryResponse(
        String gameId,
        String gameType,
        String gameMode,
        Timestamp gameStartTime,
        Long gameLength,
        List<ParticipantDto> participants
) {
}
