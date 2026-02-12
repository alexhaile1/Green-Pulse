package com.greenpulse.projectgreenpulse.entities;

/**
 *
 * @author haile
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producteur extends Utilisateur {

    private String typeCulture;

    private BigDecimal superficieExploitée;

    private boolean certificationBio;
    private List<Produit> grainOffre;

    public Producteur() {
    }

   
    public Producteur(int id, String nom, String prenom, String raisonSociale, 
                      String email, String telephone, String adresse, String pays, 
                      String motDePasse, Date dateInscription, 
                      String typeCulture, BigDecimal superficieExploitée, boolean certificationBio) {

        super(id, TypeUtilisateur.Producteur, nom, prenom, raisonSociale, email, telephone, adresse, pays, motDePasse);
        this.typeCulture = typeCulture;
        this.superficieExploitée = superficieExploitée;
        this.certificationBio = certificationBio;
        this.grainOffre = new ArrayList<>();
    }

    public Producteur(String typeCulture, BigDecimal superficieExploitée, boolean certificationBio, TypeUtilisateur typeUtilisateur, String nom, String prenom, String raisonSociale, String email, String telephone, String adresse, String pays, String motDePasse, String status) {
        super();
        this.typeCulture = typeCulture;
        this.superficieExploitée = superficieExploitée;
        this.certificationBio = certificationBio;
    }

    public String getTypeCulture() {
        return typeCulture;
    }

    public void setTypeCulture(String typeCulture) {
        this.typeCulture = typeCulture;
    }

    public BigDecimal getSuperficieExploitée() {
        return superficieExploitée;
    }

    public void setSuperficieExploitée(BigDecimal superficieExploitée) {
        this.superficieExploitée = superficieExploitée;
    }

    public boolean isCertificationBio() {
        return certificationBio;
    }

    public void setCertificationBio(boolean certificationBio) {
        this.certificationBio = certificationBio;
    }

    public List<Produit> getGrainOffre() {
        return grainOffre;
    }

    public void ajouterTypeGrain(Produit produit) {
        grainOffre.add(produit);
        System.out.println("Produit est ajouter : " + produit.getNomGrain());
    }

    public void mettreAJourStock(int produitId, int nouvelleQuantite) {
        for (Produit produit : grainOffre) {
            if (produit.getId() == produitId) {
                produit.setQuantiteDisponible(nouvelleQuantite);
                System.out.println("Stock mis a jour pour : " + produit.getNomGrain());
                return;
            }
        }
        System.out.println("Produit est pas trouver");
    }

    /*public void fixerPrix(int produitId, float nouveauPrix) {
        for (Produit produit : grainOffre) {
            if (produit.getId() == produitId) {
                produit.setPrixVente(nouveauPrix);
                System.out.println("Nouveau prix pour : " + produit.getNomGrain());
                return;
            }
        }
        System.out.println("Produit est pas trouver");
    }*/

    public void publierOffre() {
        for (Produit produit : grainOffre) {
            System.out.println("Offre publier : " + produit.getNomGrain() + " - " + produit.getPrixVente() + " $");
        }
    }

@Override
public String toString() {
    return "Producteur{" +
            "id=" + getId() +
            ", nom='" + getNom() + '\'' +
            ", prenom='" + getPrenom() + '\'' +
            ", email='" + getEmail() + '\'' +
            ", adresse='" + getAdresse() + '\'' +
            ", pays='" + getPays() + '\'' +
            ", typeCulture='" + typeCulture + '\'' +
            ", superficieExploitée=" + superficieExploitée +
            ", certificationBio=" + certificationBio +
            ", nombre de produits=" + (grainOffre != null ? grainOffre.size() : 0) + // ✅ Évite NullPointerException
            '}';
    }

}