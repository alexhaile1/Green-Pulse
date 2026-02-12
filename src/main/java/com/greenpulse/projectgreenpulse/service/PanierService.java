package com.greenpulse.projectgreenpulse.service;

import com.greenpulse.projectgreenpulse.entities.Panier;
import com.greenpulse.projectgreenpulse.entities.Produit;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class PanierService {

    private static final String SESSION_PANIER = "panierActuel";

    public Panier obtenirPanier(HttpSession session) {
        Panier panier = (Panier) session.getAttribute(SESSION_PANIER);
        if (panier == null) {
            panier = new Panier(0);
            session.setAttribute(SESSION_PANIER, panier);
        }
        return panier;
    }

    public void ajouterAuPanier(HttpSession session, Produit produit, int quantite) {
        Panier panier = obtenirPanier(session);
        panier.ajouterProduitPanier(produit, quantite);
    }

    public void modifierQuantitePanier(HttpSession session, Produit produit, int nouvelleQuantite) {
        Panier panier = obtenirPanier(session);
        panier.modifierQuantitePanier(produit, nouvelleQuantite);
    }

    public void retirerProduitPanier(HttpSession session, Produit produit) {
        Panier panier = obtenirPanier(session);
        panier.supprimerProduitPanier(produit);
    }

    public void viderPanier(HttpSession session) {
        Panier panier = obtenirPanier(session);
        panier.viderPanier();
    }
}