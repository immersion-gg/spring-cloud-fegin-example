package com.example.openfeign.match.infra;

import java.util.List;
public record MatchQueryResponse(
        List<String> matchIds) {

    public List<String> getMatchIds() {
        return matchIds;

    }

}
