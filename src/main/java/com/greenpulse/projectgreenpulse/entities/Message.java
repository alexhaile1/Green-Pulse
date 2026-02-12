package com.greenpulse.projectgreenpulse.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author haile
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String emailEnvoyeur;

    @Column(nullable = false)
    private String emailRecepteur;


    @Column(length = 64, nullable = false)
    private String contenuMessage;

    @Temporal(TemporalType.DATE)
    @Column(length = 128, nullable = false)
    private Date dateEnvoi;

    public Message() {
    }

    public Message(String emailRecepteur, String emailEnvoyeur, String contenuMessage) {
        this.emailRecepteur = emailRecepteur;
        this.emailEnvoyeur = emailEnvoyeur;
        this.contenuMessage = contenuMessage;
        this.dateEnvoi = new Date();
    }

    public Message(int id, String emailRecepteur,  String emailEnvoyeur, String contenuMessage, Timestamp dateEnvoi) {
        this.id = id;
        this.emailRecepteur = emailRecepteur;
        this.emailEnvoyeur = emailEnvoyeur;
        this.contenuMessage = contenuMessage;
        this.dateEnvoi = new Date(dateEnvoi.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailEnvoyeur() {
        return emailEnvoyeur;
    }

    public void setEmailEnvoyeur(String emailEnvoyeur) {
        this.emailEnvoyeur = emailEnvoyeur;
    }

    public String getEmailRecepteur() {
        return emailRecepteur;
    }

    public void setEmailRecepteur(String emailRecepteur) {
        this.emailRecepteur = emailRecepteur;
    }

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", emailEnvoyeur=" + emailEnvoyeur +
                ", emailRecepteur=" + emailRecepteur +
                ", contenuMessage='" + contenuMessage + '\'' +
                ", dateEnvoi=" + dateEnvoi +
                '}';
    }
}
