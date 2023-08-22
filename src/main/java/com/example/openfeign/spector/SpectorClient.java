package com.example.openfeign.spector;

import com.example.openfeign.config.RiotFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@FeignClient(name = "match", configuration = RiotFeignConfiguration.class, url = "${riot.api.AsiaUrl}")
public interface SpectorClient {

    @GetMapping("/lol/spectator/v4/active-games/by-summoner/{id}")
    List<String> getMatchIdsByPUUID(@RequestParam String id);

}


