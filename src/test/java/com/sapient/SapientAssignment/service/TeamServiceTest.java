package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.League;
import com.sapient.SapientAssignment.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class TeamServiceTest {

    @InjectMocks
    TeamService teamService;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void testGetTeamDetailsWithOK(){
        Team team = new Team();
        team.setTeam_key("1234");
        team.setTeam_name("CHELSEA");
        teamService.setApiUrl("https://localhost:8080/");
        Team[] teamArr = new Team[]{team};
        ResponseEntity<Team[]> responseEntity = new ResponseEntity<>(teamArr, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<Team[]>> any())).thenReturn(responseEntity);
        Assert.notEmpty(teamService.getTeamDetails("1234"),"Not Empty");
    }

    @Test
    public void testGetTeamDetailsWithNotFound(){
        Team team = new Team();
        team.setTeam_key("1234");
        team.setTeam_name("CHELSEA");
        teamService.setApiUrl("https://localhost:8080/");
        Team[] teamArr = new Team[]{team};
        ResponseEntity<Team[]> responseEntity = new ResponseEntity<>(teamArr, null, HttpStatus.NOT_FOUND);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<Team[]>> any())).thenReturn(responseEntity);
        Assert.isTrue(teamService.getTeamDetails("1234").isEmpty(),"Not Empty");
    }

}
