package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.League;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class LeagueServiceTest {

    @InjectMocks
    LeagueService leagueService;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void testGetLeagueDetailsWithOk(){
        League league = new League();
        league.setCountry_id("1234");
        league.setCountry_name("ENGLAND");
        league.setLeague_id("12345");
        leagueService.setApiUrl("https://localhost:8080/");
        League[] leagueArr = new League[]{league};
        ResponseEntity<League[]> responseEntity = new ResponseEntity<>(leagueArr, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<League[]>> any())).thenReturn(responseEntity);
        Assert.notEmpty(leagueService.getLeagueDetails("1234"),"Not Empty");
    }

    @Test
    public void testGetLeagueDetailsWithError(){
        League league = new League();
        league.setCountry_id("1234");
        league.setCountry_name("ENGLAND");
        league.setLeague_id("12345");
        leagueService.setApiUrl("https://localhost:8080/");
        League[] leagueArr = new League[]{league};
        ResponseEntity<League[]> responseEntity = new ResponseEntity<>(leagueArr, null, HttpStatus.NOT_FOUND);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<League[]>> any())).thenReturn(responseEntity);
        Assert.isTrue(leagueService.getLeagueDetails("1234").isEmpty(),"Not Empty");
    }

}
