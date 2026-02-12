package com.greenpulse.projectgreenpulse.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="logistique")
public class Logistique {

    @Id
    private Long id;

    @Column
    private int transactionId;

    @Column(length = 128, nullable = false)
    private String transporteur;

    @Column
    private BigDecimal fraisTransport;

    @Column
    private int delai;

    @Column(length = 128, nullable = false)
    private String statutLivraison;

    public Logistique() {}

    public Logistique(Long id, int transactionId, String transporteur,
                      BigDecimal fraisTransport, int delai, String statutLivraison) {
        this.id = id;
        this.transactionId = transactionId;
        this.transporteur = transporteur;
        this.fraisTransport = fraisTransport;
        this.delai = delai;
        this.statutLivraison = statutLivraison;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransporteur() {
        return transporteur;
    }

    public void setTransporteur(String transporteur) {
        this.transporteur = transporteur;
    }

    public BigDecimal getFraisTransport() {
        return fraisTransport;
    }

    public void setFraisTransport(BigDecimal fraisTransport) {
        this.fraisTransport = fraisTransport;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison(String statutLivraison) {
        this.statutLivraison = statutLivraison;
    }

    public void mettreAJourStatut(String nouveauStatut) {
        this.statutLivraison = nouveauStatut;
        System.out.println("Statut mis à jour : " + this.statutLivraison);
    }

    public void confirmerLivraison(Date dateReelle) {
        this.statutLivraison = "Livrée";
        System.out.println("Transaction " + this.transactionId + " livrée le " + dateReelle);
    }

    public void annulerLivraison() {
        this.statutLivraison = "Annulée";
        System.out.println("La livraison pour la transaction " + this.transactionId + " a été annulée.");
    }

    @Override
    public String toString() {
        return "Logistique{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", transporteur=" + transporteur +
                ", fraisTransport=" + fraisTransport +
                ", delai=" + delai +
                ", statutLivraison=" + statutLivraison + '}';
    }
}