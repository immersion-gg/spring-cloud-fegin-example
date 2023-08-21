package com.example.openfeign.summoner.match;

public record MatchQueryResponse(
      MetadataQueryResponse metadata,
      MatchInfoQueryResponse info
) {
}
