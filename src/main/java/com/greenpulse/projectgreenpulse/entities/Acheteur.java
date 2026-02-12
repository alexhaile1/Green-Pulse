package com.greenpulse.projectgreenpulse.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Acheteur extends Utilisateur {

    private String typeEntreprise;

    private BigDecimal volumeAchatMensuel;

    private List<Transaction> transactions;

    public Acheteur() {
        this.transactions = new ArrayList<>();
    }

    public Acheteur(int id, String nom, String prenom, String raisonSociale,
            String email, String telephone, String adresse, String pays,
            String motDePasse, Date dateInscription,
            String typeEntreprise, BigDecimal volumeAchatMensuel) {
        super(id, TypeUtilisateur.Acheteur, nom, prenom, raisonSociale, email, telephone, adresse, pays, motDePasse);
        this.typeEntreprise = typeEntreprise;
        this.volumeAchatMensuel = volumeAchatMensuel;
        this.transactions = new ArrayList<>();
    }

    public Acheteur(String typeEntreprise, TypeUtilisateur typeUtilisateur, String nom, String prenom, String raisonSociale, String email, String telephone, String adresse, String pays, String motDePasse, String status) {
        super();
        this.typeEntreprise = typeEntreprise;
    }    
    

    public String getTypeEntreprise() {
        return typeEntreprise;
    }

    public void setTypeEntreprise(String typeEntreprise) {
        this.typeEntreprise = typeEntreprise;
    }

    public BigDecimal getVolumeAchatMensuel() {
        return volumeAchatMensuel;
    }

    public void setVolumeAchatMensuel(BigDecimal volumeAchatMensuel) {
        this.volumeAchatMensuel = volumeAchatMensuel;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void rechercherGrain(String typeGrain) {
        System.out.println("Recherche grains de type : " + typeGrain);
    }

    /*public void passerCommande(Transaction transaction, Produit produit, int quantite, float prixNegocie) {
        transaction.ajouterProduit(produit, quantite, prixNegocie);
        transactions.add(transaction);
        System.out.println("Transaction est cree : " + transaction.getId()
                + "  " + transaction.getProduits().size() + " ");
    }*/

   /* public void negocierPrix(int transactionId, Produit produit, float nouveauPrix) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == transactionId) {
                int index = transaction.getProduits().indexOf(produit);
                if (index != -1) {
                    transaction.getPrixNegocies().set(index, nouveauPrix);
                    transaction.mettreAJourPrixTotal();
                    System.out.println("le prix a ete negocie pour le produit " + produit.getNomGrain() + " : " + nouveauPrix + "$");
                    return;
                }
            }
        }
        System.out.println("Transaction ou produit pas trouve");
    }*/

    public String afficherTitreDesColonnes() {
        String message = String.format(
                " %-5s %-15s %-12s %-25s %-30s %-15s %-20s %-20s %-10s",
                "Id", "Nom", "PrÃ©nom", "Email", "Adresse", "Pays",
                "Type Entreprise", "Volume Achat", "Nb Transactions"
        );

        message += "\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

        return message;
    }

    @Override
    public String toString() {
        String message = String.format(
                " %-5d %-15s %-12s %-25s %-30s %-15s %-20s %-20.2f %-10d",
                this.getId(), this.getNom(), this.getPrenom(), this.getEmail(), this.getAdresse(),
                this.getPays(), this.typeEntreprise, this.volumeAchatMensuel,
                this.transactions.size()
        );
        return message;
    }
}