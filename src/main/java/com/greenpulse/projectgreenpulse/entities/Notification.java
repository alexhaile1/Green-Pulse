package com.greenpulse.projectgreenpulse.entities;

import java.util.Date;

/**
 *
 * @author leen
 */
public class Notification {
    
    // Déclaration des variables
    private int id;
    private int utilisateur_id;
    private String typeNotification;
    private String message;
    private Date dateEnvoi;
    private String statut;
    
    // Méthodes constructeurs
    public Notification(){
    }
    
    public Notification(int utilisateur_id, String message){
        this.utilisateur_id = utilisateur_id;
        this.message = message;
    }
    
    public Notification(int id, int utilisateur_id, String typeNotification, String message, Date dateEnvoi, String statut) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.typeNotification = typeNotification;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.statut = statut;
    }
    
    // Méthode accesseurs
    public int getId() {
        return id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public String getStatut() {
        return statut;
    }
    
    // Méthodes mutateurs
    public void setId(int id) {
        this.id = id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    // Autres méthodes
    public Notification envoyerNotification(Utilisateur utilisateur, String message){
        Notification notification = null;
        if(utilisateur != null){
           notification = new Notification(utilisateur.getId(), message);
        }
        return notification;
    }
    
    public String marquerCommeLue(){
        return statut = "Lu";
    }
    
    
    // Méthode toString
    @Override
    public String toString(){
        return "";
    }   
}