package com.example.openfeign.summoner.match;

import java.util.List;

public record MatchInfoQueryResponse(
        long gameCreation,
        long gameDuration,
        List<ParticipantsQueryResponse> participants,
        List<TeamQueryResponse> teams
) {
}
