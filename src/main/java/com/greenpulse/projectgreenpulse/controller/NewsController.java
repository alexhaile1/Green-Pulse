package com.greenpulse.projectgreenpulse.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class NewsController {

    @Value("${newsapi.key}")
    private String newsApiKey;

    @GetMapping("/actualiteAgricole")
    public String newsActualiteAgricole() {
        return "actualiteAgricole";
    }

    // Appel l'api externe avec rest (classe fournie par spring) en utilisant ma cle
    @GetMapping("/api/agriculture-news")
    @ResponseBody
    public ResponseEntity<String> getAgricultureNews() {
        String apiUrl = "https://newsapi.org/v2/everything?q=agriculture&language=fr&pageSize=8&apiKey=4e72777a6b5c46f6b530ac421a355e58";

        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(apiUrl, String.class);
            return ResponseEntity.ok(response);
        } catch (RestClientException e) {
            System.err.println("Erreur de newsapi: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Failed to fetch news data\", \"details\":\"" +
                            e.getMessage().replace("\"", "'") + "\"}");
        }
    }
}