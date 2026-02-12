package com.greenpulse.projectgreenpulse.service;

import com.greenpulse.projectgreenpulse.entities.Produit;
import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.repos.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/* Auteurs : Yanis et Mariyam */
@Service
@Transactional
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    // MÉTHODES DE MARIYAM
    // Affichage des produits dans la page des produits
    public List<Produit> afficherProduits() {
        return produitRepository.findAll();
    }

    // Trouver un produit par son id
    public Produit trouverProduit(Integer id) {
        Produit produit = produitRepository.findById(id).get();
        return produit;
    }

    // Trouver produit par son nom
    public Produit getProduitParNom(String nom) {
        return produitRepository.getProduitNom(nom);
    }

    // Enregistrer un produit
    public Produit enregister(Produit produit) {
        return produitRepository.save(produit);
    }

    // Supprimer un produit par son id
    public void supprimerProduit(Integer id) {
        Long countById = produitRepository.countById(id);
        // Si il n'y a pas de produit dans la bd
        if (countById == null || countById == 0) {
            System.out.println("Le produit n'existe pas");
        }
        produitRepository.deleteById(id);
    }

    // Rechercher un produit à partir de la liste
    public List<Produit> rechercherProduit(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return produitRepository.findAll(keyword);
        }
        return null;
    }

    // Méthode qui vérifie si on ajoute un produit doublon
    public boolean isProduitUnique(String nom,Integer id) {
        Produit produitByNom =  produitRepository.getProduitNom(nom);
        if (produitByNom == null) return true;
        boolean isCreatingNewProduct = false;
        if (id == null){
            isCreatingNewProduct=true;
        }
        if(isCreatingNewProduct){
            if (produitByNom != null) return false;
        }else{

            if (produitByNom.getId() != id) {
                return false;
            }
        }
        return true;
    }

    // MÉTHODES DE YANIS
    // Limite a 3 l'affichage
    public List<Produit> produitsTop3Cher() {
        List<Produit> products = produitRepository.findByOrdrePlusCher();
        return products.stream().limit(3).toList();
    }

    // Limite a 3 l'affichage
    public List<Produit> produitsTop3PasCher() {
        List<Produit> products = produitRepository.findByOrdreMoinsCher();
        return products.stream().limit(3).toList();
    }

    // Methode qui récupère le 1er de la liste
    public Produit recommandationMarche() {
        return this.produitsTop3Cher()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucun produit"));
    }

    // Methode qui récupère le 1er de la liste
    public Produit recommandationPasMarche() {
        return this.produitsTop3PasCher()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucun produit"));
    }

    // Crée un hashmap pour stocker les stats dans des string objects
    public Map<String, Object> afficherProduitStatistiques() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProduits", produitRepository.count());
        stats.put("produitsParType", produitRepository.produitParTypeGrain());
        stats.put("prixMoyenParType", produitRepository.produitParPrixVente());
        return stats;
    }

    // Produit par id
    public Produit getProduitById(int id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    // Produit par email
    public List<Produit> rechercherProduitsProducteur(String producteurEmail) {
        return produitRepository.findByProducteurEmail(producteurEmail);
    }
}