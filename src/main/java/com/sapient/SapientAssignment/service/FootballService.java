package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.Country;
import com.sapient.SapientAssignment.model.League;
import com.sapient.SapientAssignment.model.Standing;
import com.sapient.SapientAssignment.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FootballService {

    @Value("${api.football.url}")
    private String apiUrl;

    @Value("${security.api.key}")
    private String apiKey;

    private CountryService countryService;
    private LeagueService leagueService;
    private TeamService teamService;
    private StandingService standingService;

    @Autowired
    FootballService(CountryService countryService, LeagueService leagueService, TeamService teamService, StandingService standingService) {
        this.countryService = countryService;
        this.leagueService = leagueService;
        this.teamService = teamService;
        this.standingService = standingService;
    }


    public String getOverallStandingResponse(String countryName, String leagueName, String teamName) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Country> countryList = countryService.getCountriesDetails();
        Optional<Country> relevantCountry = countryList.stream().filter(c -> c.getCountry_name().equals(countryName)).findAny();
        if (relevantCountry.isPresent()) {
            stringBuilder.append(relevantCountry.get().getCountry_id()).append("-").append(countryName).append("\n");
            List<League> leagueList = leagueService.getLeagueDetails(relevantCountry.get().getCountry_id());
            Optional<League> relevantLeague = leagueList.stream().filter(league -> league.getLeague_name().equals(leagueName)).findAny();
            if (relevantLeague.isPresent()) {
                stringBuilder.append(relevantLeague.get().getLeague_id()).append("-").append(leagueName).append("\n");
                List<Team> teamList = teamService.getTeamDetails(relevantLeague.get().getLeague_id());
                Optional<Team> relavantTeam = teamList.stream().filter(team->team.getTeam_name().equals(teamName)).findAny();
                if(relavantTeam.isPresent()){
                    stringBuilder.append(relavantTeam.get().getTeam_key()).append("-").append(teamName).append("\n");
                }
                List<Standing> standingList = standingService.getStandingDetails(relevantLeague.get().getLeague_id());
                Optional<Standing> relavantStanding = standingList.stream().filter(standing -> standing.getTeam_id().equals(relavantTeam.get().getTeam_key())).findAny();
                if (relavantStanding.isPresent()){
                    stringBuilder.append(relavantStanding.get().getOverall_league_position());
                }
            }
        }
        return stringBuilder.toString();
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
