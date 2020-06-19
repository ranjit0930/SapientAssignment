package com.sapient.SapientAssignment.service;

import com.sapient.SapientAssignment.model.Standing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandingService {
    @Value("${api.football.url}")
    private String apiUrl;

    @Value("${security.api.key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(StandingService.class);

    public List<Standing> getStandingDetails(String leagueId) {
        logger.info("Getting Standing Details.....");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("action", "get_standings")
                .queryParam("league_id", leagueId)
                .queryParam("APIkey", apiKey);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Standing>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Standing>>() {
                });
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }
}
