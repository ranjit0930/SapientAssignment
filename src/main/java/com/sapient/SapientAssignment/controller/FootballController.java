package com.sapient.SapientAssignment.controller;

import com.sapient.SapientAssignment.model.Country;
import com.sapient.SapientAssignment.service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FootballController {
    @Autowired
    private FootballService footballService;

    @GetMapping("/teamStandings")
    public String fetchTeamStanding(@RequestParam String countryName,@RequestParam String leagueName,@RequestParam String teamName){
      return footballService.getOverallStandingResponse(countryName,leagueName,teamName);
    }
}
