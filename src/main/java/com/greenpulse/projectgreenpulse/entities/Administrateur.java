package com.greenpulse.projectgreenpulse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author haile
 */

public class Administrateur extends Utilisateur {

    public Administrateur() {
    }

    public Administrateur(int id, String nom, String prenom, String raisonSociale, String email, String telephone, String adresse, String pays, String motDePasse) {
        super(id, TypeUtilisateur.Administrateur, nom, prenom, raisonSociale, email, telephone, adresse, pays, motDePasse);
    }

    public Administrateur(TypeUtilisateur typeUtilisateur, String nom, String prenom, String raisonSociale, String email, String telephone, String adresse, String pays, String motDePasse, String status) {
        super();
    }

    public void supprimerCompte(Utilisateur utilisateur) {
        System.out.println("Le compte de " + utilisateur.getNom() + " " + utilisateur.getPrenom() + " a ete supprimer.");
    }

    public void suspendreCompte(Utilisateur utilisateur) {
        System.out.println("Le compte de " + utilisateur.getNom() + " " + utilisateur.getPrenom() + " a ete suspendu.");
    }

    public void approuverCompte(Utilisateur utilisateur) {
        System.out.println("Le compte de " + utilisateur.getNom() + " " + utilisateur.getPrenom() + " a ete approuve.");
    }

    public void modererTransactions(Transaction transaction) {
        System.out.println("Superviser la transaction ID: " + transaction.getId()
                + " entre l'acheteur ID " + transaction.getAcheteurId()
                + " et le producteur id " + transaction.getProducteurId());
    }

    public void gererLitige(Transaction transaction) {
        System.out.println("Gerer en cours pour la transaction ID: " + transaction.getId());
    }

    public String afficherTitreDesColonnes() {
        String message = String.format(
                " %-5s %-12s %-12s %-18s %-12s %-15s %-10s %-12s",
                "Id", "Nom", "Prenom",
                "Email", "Téléphone", "Adresse", "Pays", "MotDePasse"
        );

        message += "\n--------------------------------------------------------------------------------------------------------";

        return message;
    }

    @Override
    public String toString() {
        String message = String.format(
                " %-5d %-12s %-12s %-18s %-12s %-15s %-10s %-12s",
                this.getId(), this.getNom(), this.getPrenom(), this.getEmail(),
                this.getTelephone(), this.getAdresse(), this.getPays(), this.getMotDePasse());
        return message;
    }
}