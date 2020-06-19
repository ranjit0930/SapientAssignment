package com.sapient.SapientAssignment.controller;

import com.sapient.SapientAssignment.model.Country;
import com.sapient.SapientAssignment.service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FootballController {
    @Autowired
    private FootballService footballService;

    @GetMapping("/teamStandings")
    public ResponseEntity<String> fetchTeamStanding(@RequestParam(required = true) String countryName, @RequestParam(required = true) String leagueName, @RequestParam(required = true) String teamName){

        try {
            String details = footballService.getOverallStandingResponse(countryName,leagueName,teamName);
            return StringUtils.isEmpty(details)? ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Content Found"): ResponseEntity.status(HttpStatus.OK).body(details);
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

    }
}
