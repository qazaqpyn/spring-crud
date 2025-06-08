package com.example.nobsv2.nobsv2.catfacts;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.nobsv2.nobsv2.Query;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {
    private final RestTemplate restTemplate;
    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENTHG = "max_length";

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam(MAX_LENTHG, input).build().toUri();

        HttpHeaders header = new HttpHeaders();
        header.set("Accept", "applicationf/json");

        HttpEntity<String> entity = new HttpEntity<>(header);

        try {
            ResponseEntity<CatFactResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity,
                    CatFactResponse.class);

            return ResponseEntity.ok(new CatFactDTO(response.getBody().getFact()));
        } catch (Exception e) {
            throw new RuntimeException("Catfact is down");
        }
    }

}
