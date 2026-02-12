package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.Produit;
import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.service.ProduitService;
import com.greenpulse.projectgreenpulse.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Auteurs : Yanis et Mariyam */

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private UtilisateurService utilisateurService;

    // MÉTHODES DE MARIYAM
    // Retourne la page des produits
    @GetMapping("/produits")
    public String produits(Model model) {
        List<Produit> listeProduits = produitService.afficherProduits();
        model.addAttribute("listeProduits", listeProduits);
        return "produits";
    }

    // Retourne le form de l'ajout d'un nouveau produit
    @GetMapping("/ajouterProduit/new")
    public String ajouterProduit(Model model) {
        Produit produit = new Produit();
        model.addAttribute("produit", produit);
        return "ajouterProduit";
    }

    // Méthode pour l'ajout d'un nouveau produit
    @PostMapping("/produits/save")
    public String ajouterProduitAction(Produit produit, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        String emailConnecte = (String) session.getAttribute("email");
        if (emailConnecte == null) return "redirect:/connexion";

        // Vérifier si le produit existe déjà
        Produit produitExistant = produitService.getProduitParNom(produit.getNomGrain());
        if (produitExistant != null) {
            String erreur = "Ce produit existe déjà.";
            //afficher l'erreur si le courriel existe deja
            model.addAttribute("erreur", erreur);
            return "ajouterProduit";
        }

        produitService.enregister(produit);
        model.addAttribute("produit", produit);
        redirectAttributes.addFlashAttribute("message", "La modification a été effectué avec succès!");
        return "redirect:/produitsProducteur";
    }

    // Méthode qui enregistre un produit pour la modification d'un produit dans la page des produits du producteur
    @PostMapping("/produits/edit/{id}")
    public String modifierProduitAction(@PathVariable Integer id, Model model, @ModelAttribute Produit produitModifier, RedirectAttributes redirectAttributes) {
        produitService.enregister(produitModifier);
        model.addAttribute("produit", produitModifier);
        redirectAttributes.addFlashAttribute("message", "Le produit a été modifié avec succès!");
        return "redirect:/produitsProducteur";
    }

    // Retourne le form de modification d'un produit
    @GetMapping("/modifierProduit")
    public String modifierProduit(Model model, @SessionAttribute(name = "email", required = false) String producteurEmail) {
        List<Produit> listeProduits = produitService.rechercherProduitsProducteur(producteurEmail);
        model.addAttribute("listeProduits", listeProduits);
        return "modifierProduit";
    }

    // Retourne la page pour supprimer un produit
    @GetMapping("/supprimerProduit")
    public String supprimerProduit(Model model, @SessionAttribute(name = "email", required = false) String producteurEmail) {
        List<Produit> listeProduits = produitService.rechercherProduitsProducteur(producteurEmail);
        model.addAttribute("listeProduits", listeProduits);
        return "supprimerProduit";
    }

    // Retourne la page pour supprimer un produit dans le portail Admin
    @GetMapping("/supprimerProduitAdmin")
    public String supprimerProduitAdmin(Model model) {
        List<Produit> listeProduits = produitService.afficherProduits();
        model.addAttribute("listeProduits", listeProduits);
        return "supprimerProduitAdmin";
    }

    // Méthode qui supprime un produit de la liste des produits
    @GetMapping("produits/delete/{id}")
    public String supprimerProduitAction(Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        produitService.supprimerProduit(id);
        redirectAttributes.addFlashAttribute("message", "Le produit a été supprimer avec succès");
        return "redirect:/produitsProducteur";
    }

    // Méthode qui supprime un produit de la liste des produits
    @GetMapping("produitsAdmin/delete/{id}")
    public String supprimerProduitAdmin(Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        produitService.supprimerProduit(id);
        redirectAttributes.addFlashAttribute("message", "Le produit a été supprimer avec succès");
        return "redirect:/supprimerProduitAdmin";
    }

    // Retourne le form de modification d'un produit d'un acheteur
    @GetMapping("/produitsAcheteur")
    public String produitsAcheteur(Model model) {
        List<Produit> listeProduits = produitService.afficherProduits();
        model.addAttribute("listeProduits", listeProduits);
        return "produitsAcheteur";
    }

    //    // Retourne le form de modification d'un produit d'un producteur
    @GetMapping("/produitsProducteur")
    public String produitsProducteur(Model model, @SessionAttribute(name = "email", required = false) String producteurEmail) {
        List<Produit> listeProduits = produitService.rechercherProduitsProducteur(producteurEmail);
        model.addAttribute("listeProduits", listeProduits);
        return "produitsProducteur";
    }

    // Retourne la page de produit selon le produit recherché par son nom
    @GetMapping("/rechercher/produits")
    public String rechercherProduits(String keyword, Model model, RedirectAttributes redirectAttributes) {
        if (keyword.isBlank()) {
            //redirection avec un message
            redirectAttributes.addFlashAttribute("message", "Inserer le nom de produit!");
            return "redirect:/produits";
        }

        List<Produit> listeProduits = produitService.rechercherProduit(keyword);
        model.addAttribute("listeProduits", listeProduits);

        if (listeProduits.isEmpty()) {
            //redirection avec un message
            redirectAttributes.addFlashAttribute("message", "Le produit est inexistant!");
            return "redirect:/produits";
        }

        return "produits";
    }

    // Retourne le page de produitsAcheteur selon le produit recherché par son nom
    @GetMapping("/rechercher/produitsAcheteur")
    public String rechercherProduitsAcheteur(String keyword, Model model, RedirectAttributes redirectAttributes) {
        if (keyword.isBlank()) {
            //redirection avec un message
            redirectAttributes.addFlashAttribute("message", "Inserer le nom de produit!");
            return "redirect:/produitsAcheteur";
        }

        List<Produit> listeProduits = produitService.rechercherProduit(keyword);
        model.addAttribute("listeProduits", listeProduits);

        if (listeProduits.isEmpty()) {
            //redirection avec un message
            redirectAttributes.addFlashAttribute("message", "Le produit est inexistant!");
            return "redirect:/produitsAcheteur";
        }

        return "produitsAcheteur";
    }


    // Retourne le page de produitsProducteur selon le produit recherché par son nom
    @GetMapping("/rechercher/produitsProducteur")
    public String rechercherProduitsProducteur(String keyword, Model model, @SessionAttribute(name = "email", required = false) String producteurEmail, RedirectAttributes redirectAttributes) {
        if (keyword.isBlank()) {
            //redirection avec un message
            redirectAttributes.addFlashAttribute("message", "Inserer le nom de produit!");
            return "redirect:/produitsProducteur";
        }

        List<Produit> listeProduits = produitService.rechercherProduitsProducteur(keyword);
        model.addAttribute("listeProduits", listeProduits);

        if (listeProduits.isEmpty()) {
            //redirection avec un message
            redirectAttributes.addFlashAttribute("message", "Le produit est inexistant!");
            return "redirect:/produitsProducteur";
        }

        return "produitsProducteur";
    }

    // MÉTHODES DE YANIS
    // Retourne tableau des surveillances des prix des produits
    @GetMapping("/surveillancePrix")
    public String surveillancePrix(Model model) {
        List<Produit> produits = produitService.afficherProduits();

        Map<String, String> producteurNoms = new HashMap<>();
        for (Produit produit : produits) {
            if (!producteurNoms.containsKey(produit.getProducteurEmail())) {
                Utilisateur producteur = utilisateurService.getUtilisateurByEmail(produit.getProducteurEmail());
                if (producteur != null) {
                    String nomComplet = producteur.getPrenom() + " " + producteur.getNom();
                    producteurNoms.put(produit.getProducteurEmail(), nomComplet);
                }
            }
        }

        model.addAttribute("stats", produitService.afficherProduitStatistiques());
        model.addAttribute("produits", produits);
        model.addAttribute("producteurNoms", producteurNoms);

        return "surveillancePrix";
    }



    // Retourne les tendances des ventes des produits, les top 3 et les recommandations du marché
    @GetMapping("/tendanceVente")
    public String tendanceProduit(Model model) {
        List<Produit> produitCher = produitService.produitsTop3Cher();
        List<Produit> produitPasCher = produitService.produitsTop3PasCher();
        Produit recommandationProduit = produitService.recommandationMarche();
        Produit recommandationPasProduit = produitService.recommandationPasMarche();

        model.addAttribute("listePlusCher", produitCher);
        model.addAttribute("listeMoinsCher", produitPasCher);
        model.addAttribute("listeRecommandationProduit", recommandationProduit);
        model.addAttribute("listeRecommandationPasProduit", recommandationPasProduit);

        return "tendanceVente";
    }
}
