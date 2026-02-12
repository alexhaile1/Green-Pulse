package com.greenpulse.projectgreenpulse.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Panier {

    // Déclaration des variables
    private int id;
    private int acheteur_id;
    private List<Produit> listePanier;
    private Map<Produit, Integer> produitsQuantiteTotal = new HashMap<>();
    private int quantite_total;
    private float prix_total;

    public Panier(int id, int acheteur_id, List<Produit> listePanier, int quantite_total, float prix_total){
        this.id = id;
        this.acheteur_id = acheteur_id;
        this.listePanier = listePanier;
        this.quantite_total = quantite_total;
        this.prix_total = prix_total;
    }

    public Panier(int acheteur_id) {
        this.acheteur_id = acheteur_id;
        this.quantite_total = 0;
        this.prix_total = 0;
    }

    public Panier() {
        this.produitsQuantiteTotal = new HashMap<>();
        this.quantite_total = 0;
        this.prix_total = 0;
    }


    // Méthode accesseurs
    public int getId() {
        return id;
    }

    public int getAcheteurId(){
        return acheteur_id;
    }

    public List<Produit> getProduit() {
        return listePanier;
    }

    public float getQuanititeTotal() {
        return quantite_total;
    }

    public float getPrixTotal(){
        return prix_total;
    }

    public List<Produit> getListePanier() {
        return new ArrayList<>(produitsQuantiteTotal.keySet());
    }

    public int getQuantitePourProduit(Produit produit) {
        return produitsQuantiteTotal.getOrDefault(produit, 0);
    }



    // Méthode mutateurs
    public void setId(int id) {
        this.id = id;
    }

    public void setAcheteurId(int acheteur_id) {
        this.acheteur_id = acheteur_id;
    }

    public void setProduit(List<Produit> listePanier) {
        this.listePanier = listePanier;
    }

    public void setQuantiteTotal(int quantite_total) {
        this.quantite_total = quantite_total;
    }

    public void setPrixTotal(float prix_total){
        this.prix_total = prix_total;
    }



    // Méthode toString
    @Override
    public String toString(){
        return "Il en reste " + quantite_total + " de " + listePanier + " et c'est " + prix_total + "$" ;
    }

    // Méthode pour ajouter un produit au panier par un acheteur
    public void ajouterProduitPanier(Produit produit, int quantite) {
        int nouvelleQuantite = produitsQuantiteTotal.getOrDefault(produit, 0) + quantite;
        produitsQuantiteTotal.put(produit, nouvelleQuantite);
        mettreAJourTotaux();
    }

    // Méthode pour modifier la quantité d'un produit au panier par un acheteur
    public void modifierQuantitePanier(Produit produit, int nouvelleQuantite) {
        if (nouvelleQuantite <= 0) {
            produitsQuantiteTotal.remove(produit);
        } else {
            produitsQuantiteTotal.put(produit, nouvelleQuantite);
        }
        mettreAJourTotaux();
    }

    // Méthode pour vider le panier au complet
    public void viderPanier() {
        produitsQuantiteTotal.clear();
        mettreAJourTotaux();
    }

    // Méthode pour retirer un produit du panier
    public void supprimerProduitPanier(Produit produit) {
        produitsQuantiteTotal.remove(produit);
        mettreAJourTotaux();
    }

    // Méthode pour mettre à jour le total du panier
    public void mettreAJourTotaux() {
        this.quantite_total = 0;
        this.prix_total = 0f;

        for (Map.Entry<Produit, Integer> entry : produitsQuantiteTotal.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();
            this.quantite_total += quantite;
            this.prix_total += (produit.getPrixVente().floatValue() * quantite);
        }
    }
}