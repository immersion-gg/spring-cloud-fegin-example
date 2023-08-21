package com.example.openfeign.summoner.match;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatchController {
    @PostMapping("/search")
    public String Search(Model model, @RequestParam("summonerName") String name) {
        model.addAttribute("summonerName", name);
        return "summoner_match";
    }
}
