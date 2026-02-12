package com.greenpulse.projectgreenpulse.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/* Auteur : Mariyam */
// Source du code : https://www.geeksforgeeks.org/how-to-call-or-consume-external-api-in-spring-boot/
@Controller
public class SectionInformativeController {

    // Retourne la page de section informative du marché des grains
    @GetMapping("/sectionInformative")
    public String sectionInformative(){ return "sectionInformative"; }

    // Appel de l'api externe et publique
    @GetMapping("/api/marche-grains")
    @ResponseBody
    public ResponseEntity<String> getMarcheGrainsRiz() {
        String uri = "https://ec.europa.eu/agrifood/api/cereal/prices?memberState&years=2025&months=1&beginDate=01/01/2025&endDate=14/05/2025";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response;
    }
}
