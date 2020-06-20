package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.Country;
import com.sapient.SapientAssignment.model.League;
import com.sapient.SapientAssignment.model.Standing;
import com.sapient.SapientAssignment.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class FootballServiceTest {

    @InjectMocks
    FootballService footballService;

    @Mock
    CountryService countryService;

    @Mock
    LeagueService leagueService;

    @Mock
    StandingService standingService;

    @Mock
    TeamService teamService;


    @Test
    public void testGetOverallStandingResponseWithCountryNameNotPresent(){
        Mockito.when(countryService.getCountriesDetails()).thenReturn(Collections.emptyList());
        Assert.isTrue(footballService.getOverallStandingResponse("COUNTRY_NAME","LEAGUE_NAME","TEAM_NAME").isEmpty(),"Is Empty");
    }

    @Test
    public void testGetOverAllStandingResponseWithAllDetailsPresent(){
        Country country = new Country();
        country.setCountry_name("COUNTRY_NAME");
        country.setCountry_id("COUNTRY_ID");
        List<Country> countryList = new ArrayList<>();
        countryList.add(country);
        Mockito.when(countryService.getCountriesDetails()).thenReturn(countryList);

        Team team = new Team();
        team.setTeam_name("TEAM_NAME");
        team.setTeam_key("TEAM_ID");
        List<Team> teamList = new ArrayList<>();
        teamList.add(team);
        Mockito.when(teamService.getTeamDetails(Mockito.anyString())).thenReturn(teamList);

        League league = new League();
        league.setLeague_id("LEAGUE_ID");
        league.setCountry_name("COUNTRY_NAME");
        league.setCountry_id("COUNTRY_ID");
        league.setLeague_name("LEAGUE_NAME");
        List<League> leagueList = new ArrayList<>();
        leagueList.add(league);
        Mockito.when(leagueService.getLeagueDetails(Mockito.anyString())).thenReturn(leagueList);

        Standing standing = new Standing();
        standing.setTeam_name("TEAM_NAME");
        standing.setTeam_id("TEAM_ID");
        standing.setCountry_name("COUNTRY_NAME");
        standing.setLeague_id("LEAGUE_ID");
        standing.setLeague_name("LEAGUE_NAME");
        standing.setOverall_league_position("OVERALL_LEAGUE_POSITION");
        List<Standing> standingList = new ArrayList<>();
        standingList.add(standing);
        Mockito.when(standingService.getStandingDetails(Mockito.anyString())).thenReturn(standingList);

        String output = footballService.getOverallStandingResponse("COUNTRY_NAME","LEAGUE_NAME","TEAM_NAME");
        Assert.isTrue(output.contains("TEAM_NAME"),"Team Name is present");
        Assert.isTrue(output.contains("TEAM_ID"),"Team ID is present");
        Assert.isTrue(output.contains("COUNTRY_NAME"),"Country Name is present");
        Assert.isTrue(output.contains("COUNTRY_ID"),"Country ID is present");
        Assert.isTrue(output.contains("LEAGUE_NAME"),"League Name is present");
        Assert.isTrue(output.contains("LEAGUE_ID"),"League ID is present");
        Assert.isTrue(output.contains("OVERALL_LEAGUE_POSITION"),"OverAll Standing is present");
    }
}
