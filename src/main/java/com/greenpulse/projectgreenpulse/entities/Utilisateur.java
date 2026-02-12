package com.greenpulse.projectgreenpulse.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/* @author haile */
@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public enum TypeUtilisateur {
        Administrateur,
        Producteur,
        Acheteur
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 64, nullable = false)
    private TypeUtilisateur typeUtilisateur;

    @Column(length = 100, nullable = false)
    private String nom;

    @Column(length = 100, nullable = false)
    private String prenom;

    @Column(length = 128, nullable = true)
    private String raisonSociale;

    @Column(length = 128, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String telephone;

    @Column(length = 128, nullable = false)
    private String adresse;

    @Column(length = 128, nullable = false)
    private String pays;

    @Column(length = 128, nullable = false)
    private String motDePasse;

    @Column(length = 128, nullable = false)
    private String status;

    @Column(length = 128)
    private String typeEntreprise;

    @Column
    private BigDecimal volumeAchatMensuel;

    @Column(length = 128)
    private String typeCulture;

    @Column
    private BigDecimal superficieExploitée;

    @Column
    private boolean certificationBio;

    public Utilisateur() {

    }

    public Utilisateur(int id, TypeUtilisateur typeUtilisateur, String nom, String prenom, String raisonSociale, String email,
                       String telephone, String adresse, String pays, String motDePasse) {
        this.id = id;
        this.typeUtilisateur = typeUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.raisonSociale = raisonSociale;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.pays = pays;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(int id, TypeUtilisateur typeUtilisateur, String nom, String prenom, String raisonSociale,
                       String email, String telephone, String adresse, String pays, String motDePasse, String typeEntreprise, BigDecimal volumeAchatMensuel, String status,
                       String typeCulture, BigDecimal superficieExploitée, boolean certificationBio) {
        this.id = id;
        this.typeUtilisateur = typeUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.raisonSociale = raisonSociale;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.pays = pays;
        this.motDePasse = motDePasse;
        this.typeEntreprise = typeEntreprise;
        this.volumeAchatMensuel = volumeAchatMensuel;
        this.status = status;
        this.typeCulture = typeCulture;
        this.superficieExploitée = superficieExploitée;
        this.certificationBio = certificationBio;
    }

    public Utilisateur(TypeUtilisateur typeUtilisateur, String nom, String prenom, String raisonSociale, String email, String telephone, String adresse, String pays, String motDePasse,
                       String status, BigDecimal volumeAchatMensuel, String typeCulture, BigDecimal superficieExploitée, boolean certificationBio) {
        this.typeUtilisateur = typeUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.raisonSociale = raisonSociale;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.pays = pays;
        this.motDePasse = motDePasse;
        this.status = status;
        this.volumeAchatMensuel = volumeAchatMensuel;
        this.typeCulture = typeCulture;
        this.superficieExploitée = superficieExploitée;
        this.certificationBio = certificationBio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTypeCulture() {
        return typeCulture;
    }

    public void setTypeCulture(String typeCulture) {
        this.typeCulture = typeCulture;
    }

    public BigDecimal getSuperficieExploitée() {
        return superficieExploitée;
    }

    public void setSuperficieExploitée(BigDecimal superficieExploitée){
        this.superficieExploitée = superficieExploitée;
    }

    public boolean isCertificationBio() {
        return certificationBio;
    }

    public void setCertificationBio(boolean certificationBio) {
        this.certificationBio = certificationBio;
    }

    public boolean authentification(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }

    public void creerCompte() {
        System.out.println("le compte a etait crer pour : " + this.email);
    }

    public String afficherTitreDesColonnes() {
        String message = String.format(
                " %-5s %-15s %-10s %-10s %-20s %-30s %-15s %-20s %-10s %-15s",
                "Id", "TypeUtilisateur", "Nom", "Prenom", "Raison Sociale",
                "Email", "Téléphone", "Adresse", "Pays", "MotDePasse"
        );

        message += "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------";

        return message;
    }

    @Override
    public String toString() {
        String message = String.format(
                " %-5d %-15s %-10s %-10s %-20s %-30s %-15s %-20s %-10s %-15s",
                this.id, this.typeUtilisateur, this.nom, this.prenom,
                this.raisonSociale, this.email, this.telephone, this.adresse,
                this.pays, this.motDePasse
        );
        return message;
    }
}