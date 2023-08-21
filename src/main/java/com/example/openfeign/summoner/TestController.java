package com.example.openfeign.summoner;

import com.example.openfeign.Mapper;
import com.example.openfeign.summoner.infra.client.RiotClient;
import com.example.openfeign.summoner.infra.riotDto.CurrentGameQueryResponse;
import com.example.openfeign.summoner.infra.riotDto.DataDto;
import com.example.openfeign.summoner.infra.riotDto.SummonerQueryResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final RiotClient riotClient;
    private final Mapper mapper;

    @GetMapping("/info/{summonerName}")
    public ResponseEntity<?> getCurrentGameInfo(@PathVariable String summonerName) {

        List<String> champions = new ArrayList<>();

        mapper.getDataDto().data().values().forEach(championDto -> {
            if (championDto.key().equals("777")) {
                champions.add(championDto.name());
            }
        });

        return ResponseEntity.ok().body(champions);
    }
}
