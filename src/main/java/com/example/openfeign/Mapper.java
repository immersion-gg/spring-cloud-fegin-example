package com.example.openfeign;

import com.example.openfeign.summoner.infra.riotDto.ChampionDto;
import com.example.openfeign.summoner.infra.riotDto.DataDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Component
public class Mapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataDto getDataDto() {

        try {
            // JSON 파일 읽어오기
            ClassPathResource resource = new ClassPathResource("champion.json");
            String json = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            // JSON을 DTO 객체로 변환

            DataDto dataDto = objectMapper.readValue(json, DataDto.class);
            return dataDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
