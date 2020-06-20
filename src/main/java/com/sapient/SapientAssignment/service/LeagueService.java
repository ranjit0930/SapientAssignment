package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.Country;
import com.sapient.SapientAssignment.model.League;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LeagueService {
    @Value("${api.football.url}")
    private String apiUrl;

    @Value("${security.api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);


    public List<League> getLeagueDetails(String countryId) {
        logger.info("Getting League Details....");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("action", "get_leagues")
                .queryParam("country_id", "countryId")
                .queryParam("APIkey", apiKey);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<League[]> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                League[].class);
        if (null !=response && response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        }
        return new ArrayList<>();
    }


    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
