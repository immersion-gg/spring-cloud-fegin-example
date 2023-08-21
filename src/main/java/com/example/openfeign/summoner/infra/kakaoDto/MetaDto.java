package com.example.openfeign.summoner.infra.kakaoDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MetaDto(
        Integer totalCount,
        Integer pageable_count,
        Boolean isEnd
) {
}
