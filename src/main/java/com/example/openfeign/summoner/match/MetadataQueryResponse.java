package com.example.openfeign.summoner.match;

import java.util.List;

public record MetadataQueryResponse(
        List<String> participants
) {
}
