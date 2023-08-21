package com.example.openfeign.summoner.infra.kakaoDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentDto(
        @JsonProperty("address_name")
        String addressName,
        @JsonProperty("x")
        String x,
        @JsonProperty("y")
        String y//
) {
}
