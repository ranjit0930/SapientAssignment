package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.League;
import com.sapient.SapientAssignment.model.Standing;
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
public class StandingServiceTest {

    @InjectMocks
    StandingService standingService;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void testGetStandingDetailsWithOK(){
        Standing standing = new Standing();
        standing.setTeam_id("1234");
        standing.setTeam_name("CHELSEA");
        standingService.setApiUrl("https://localhost:8080/");
        Standing[] standingArr = new Standing[]{standing};
        ResponseEntity<Standing[]> responseEntity = new ResponseEntity<>(standingArr, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<Standing[]>> any())).thenReturn(responseEntity);
        Assert.notEmpty(standingService.getStandingDetails("1234"),"Not Empty");
    }

    @Test
    public void testGetStandingDetailsWithNotFound(){
        Standing standing = new Standing();
        standing.setTeam_id("1234");
        standing.setTeam_name("CHELSEA");
        standingService.setApiUrl("https://localhost:8080/");
        Standing[] standingArr = new Standing[]{standing};
        ResponseEntity<Standing[]> responseEntity = new ResponseEntity<>(standingArr, null, HttpStatus.NOT_FOUND);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<Standing[]>> any())).thenReturn(responseEntity);
        Assert.isTrue(standingService.getStandingDetails("1234").isEmpty(),"Empty");
    }

}
