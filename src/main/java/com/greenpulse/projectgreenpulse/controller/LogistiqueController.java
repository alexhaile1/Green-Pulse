package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.Logistique;
import com.greenpulse.projectgreenpulse.service.LogistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Auteur : Alex */

@Controller
public class LogistiqueController {

    @Autowired
    private LogistiqueService logistiqueService;

    // Affiche la commande suivi
    @GetMapping("/transactionAcheteur")
    public String suivisCommande(@RequestParam(name = "idTransaction", required = false) String idString, Model model) {
        try {
            int id = Integer.parseInt(idString);
            Logistique logistique = logistiqueService.chercherParId(id);

            if (logistique != null) {
                model.addAttribute("logistique", logistique);
            } else {
                model.addAttribute("erreur", "Aucune transaction trouver");
            }
        } catch (NumberFormatException e) {  }

        return "transactionAcheteur";
    }
}