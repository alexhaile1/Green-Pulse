package com.greenpulse.projectgreenpulse.entities;

/**
 *
 * @author haile
 */

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 128, nullable = false)
    private String nomGrain;

    @Column(length = 128, nullable = false)
    private String typeGrain;

    @Column(length = 128, nullable = false)
    private String producteurEmail;

    @Column
    private int quantiteDisponible;

    @Column
    private BigDecimal prixVente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(length = 128)
    private Date datePublication;

    @Column(length = 254, nullable = false)
    private String description;

    @ManyToMany(mappedBy = "produits")
    private List<Transaction> transactions = new ArrayList<>();

    public Produit() {
    }

    public Produit(int id, String nomGrain, String typeGrain, String producteurEmail, int quantiteDisponible, BigDecimal prixVente, Date datePublication, String description) {
        this.id = id;
        this.nomGrain = nomGrain;
        this.typeGrain = typeGrain;
        this.producteurEmail = producteurEmail;
        this.quantiteDisponible = quantiteDisponible;
        this.prixVente = prixVente;
        this.datePublication = datePublication;
        this.description = description;
    }

    public Produit(String nomGrain, String typeGrain, String producteurEmail, int quantiteDisponible, BigDecimal prixVente, String description, Date datePublication) {
        this.nomGrain = nomGrain;
        this.typeGrain = typeGrain;
        this.producteurEmail = producteurEmail;
        this.quantiteDisponible = quantiteDisponible;
        this.prixVente = prixVente;
        this.datePublication = datePublication;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomGrain() {
        return nomGrain;
    }

    public void setNomGrain(String nomGrain) {
        this.nomGrain = nomGrain;
    }

    public String getTypeGrain() {
        return typeGrain;
    }

    public void setTypeGrain(String typeGrain) {
        this.typeGrain = typeGrain;
    }

    public String getProducteurEmail() {
        return producteurEmail;
    }

    public void setProducteurEmail(String producteurEmail) { this.producteurEmail = producteurEmail; }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public BigDecimal getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(BigDecimal prixVente) {
        this.prixVente = prixVente;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Transaction> getTransactions(){ return transactions; }

    public void setTransactions(List<Transaction> listeTransactions ){ this.transactions = listeTransactions; }


    public void publierOffre() {
        System.out.println("Offre publie pour : " + nomGrain + " au prix de " + prixVente + " $");
    }

    public void modifierOffre(BigDecimal nouveauPrix, int nouvelleQuantite) {
        this.prixVente = nouveauPrix;
        this.quantiteDisponible = nouvelleQuantite;
        System.out.println("Offre mise a jour : " + nomGrain + " Prix : " + prixVente + " $, Quantite : " + quantiteDisponible);
    }

    public void supprimerOffre() {
        System.out.println("L'offre pour " + nomGrain + " a ete supprime");
    }

    public String afficherTitreDesColonnes() {
        return String.format(
                "%-5s %-15s %-15s %-15s %-15s %-15s %-20s",
                "Id", "Nom Grain", "Type Grain", "ProducteurID",
                "Quantiter", "Prix Vente", "DateProduit"
        ) + "\n"
                + "------------------------------------------------------------------------------------------------------";
    }

    // Méthodes pour être capable d'enlever un produit de la liste des produits dans le panier
    // Source : https://www.tutorialspoint.com/java/util/arraylist_remove.htm
    @Override
    public boolean equals(Object obj) {
        Produit p = (Produit) obj;
        return this.id == p.id && this.nomGrain.equalsIgnoreCase(p.nomGrain);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.nomGrain);
        return hash;
    }

    @Override
    public String toString() {
        return String.format(
                "%-5d %-15s %-15s %-15d %-15d %-15.2f %-20s",
                this.id, this.nomGrain, this.typeGrain, this.producteurEmail,
                this.quantiteDisponible, this.prixVente, this.datePublication
        );
    }
}
