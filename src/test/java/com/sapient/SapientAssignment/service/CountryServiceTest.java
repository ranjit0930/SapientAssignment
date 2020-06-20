package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.Country;
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
public class CountryServiceTest {

    @InjectMocks
    CountryService countryService;

    @Mock
    RestTemplate restTemplate;


    @Test
    public void testGetCountriesDetailsWithOk(){
        Country country = new Country();
        country.setCountry_id("1234");
        country.setCountry_name("England");
        countryService.setApiUrl("https://localhost:8080/");
        Country[] countryArr = new Country[]{country};
        ResponseEntity<Country[]> responseEntity = new ResponseEntity<>(countryArr, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<Country[]>> any())).thenReturn(responseEntity);
        Assert.notEmpty(countryService.getCountriesDetails(),"Not Empty");
    }
    @Test
    public void testGetCountriesDetailsWithNotFound(){
        Country country = new Country();
        country.setCountry_id("1234");
        country.setCountry_name("England");
        countryService.setApiUrl("https://localhost:8080/");
        Country[] countryArr = new Country[]{country};
        ResponseEntity<Country[]> responseEntity = new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        Mockito.when(restTemplate.exchange( Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.<HttpEntity<?>> any(),
                Mockito.<Class<Country[]>> any())).thenReturn(responseEntity);
        Assert.isTrue(countryService.getCountriesDetails().isEmpty(),"Empty");
    }
}
