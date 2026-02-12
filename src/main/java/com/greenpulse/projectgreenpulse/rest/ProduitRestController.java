package com.greenpulse.projectgreenpulse.rest;

import com.greenpulse.projectgreenpulse.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitRestController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/produits/check_nom")
    public String verifierDoublonEmail(@Param("nom_grain") String nom, @Param("id") Integer id) {
        return produitService.isProduitUnique(nom,id) ? "OK" : "Doublon";
    }
}
