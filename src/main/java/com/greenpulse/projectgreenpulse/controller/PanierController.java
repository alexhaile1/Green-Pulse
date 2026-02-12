package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.Panier;
import com.greenpulse.projectgreenpulse.entities.Produit;
import com.greenpulse.projectgreenpulse.entities.Transaction;
import com.greenpulse.projectgreenpulse.service.PanierService;
import com.greenpulse.projectgreenpulse.service.ProduitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Date;

/* Auteur : Yanis */

@Controller
@RequestMapping("/panier")
public class PanierController {

    private final PanierService panierService;
    private final ProduitService produitService;

    public PanierController(PanierService panierService, ProduitService produitService) {
        this.panierService = panierService;
        this.produitService = produitService;
    }

    // Affiche le contenu du panier de la session
    @GetMapping
    public String afficherPanier(Model model, HttpSession session) {
        Panier panier = panierService.obtenirPanier(session);
        model.addAttribute("panier", panier);
        return "panier";
    }

    // Permet d'ajouter un produit au panier depuis un compte Acheteur
    @PostMapping("/ajouter/{produitId}")
    public String ajouterProduitPanier(@PathVariable int produitId, @RequestParam(defaultValue = "1") int quantite, HttpSession session, HttpServletRequest request) {
        Produit produit = produitService.getProduitById(produitId);
        panierService.ajouterAuPanier(session, produit, quantite);
        return "redirect:" + request.getHeader("Referer");
    }

    // Modifie dans le panier la quantité des produits avec son id
    @PostMapping("/modifier-quantite/{produitId}")
    public String modifierQuantitePanier(@PathVariable int produitId, @RequestParam int quantite, HttpSession session) {
        Produit produit = produitService.getProduitById(produitId);
        panierService.modifierQuantitePanier(session, produit, quantite);
        return "redirect:/panier";
    }

    // Retire dans le panier le produit avec son id
    @PostMapping("/retirer/{produitId}")
    public String retirerProduitPanier(@PathVariable int produitId, HttpSession session) {
        Produit produit = produitService.getProduitById(produitId);
        panierService.retirerProduitPanier(session, produit);
        return "redirect:/panier";
    }

    // Vide la panier
    @PostMapping("/vider")
    public String viderPanier(HttpSession session) {
        panierService.viderPanier(session);
        return "redirect:/panier";
    }
}