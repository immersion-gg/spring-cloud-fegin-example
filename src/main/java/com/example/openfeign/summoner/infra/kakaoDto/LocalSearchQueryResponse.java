package com.example.openfeign.summoner.infra.kakaoDto;

import com.example.openfeign.summoner.infra.kakaoDto.DocumentDto;
import com.example.openfeign.summoner.infra.kakaoDto.MetaDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record LocalSearchQueryResponse(
        @JsonProperty("meta")
        MetaDto metaDto,
        @JsonProperty("documents")
        List<DocumentDto> documentDtoList
) {
}
