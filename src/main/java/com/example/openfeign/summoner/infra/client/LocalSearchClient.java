package com.example.openfeign.summoner.infra.client;

import com.example.openfeign.config.KakaoFeignConfiguration;
import com.example.openfeign.summoner.infra.kakaoDto.LocalSearchQueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "localSearch", url = "${kakao.api.url}", configuration = {KakaoFeignConfiguration.class})
public interface LocalSearchClient {

    @GetMapping("/v2/local/search/address.json")
    LocalSearchQueryResponse getAddress(@RequestParam("query") String address);

}
