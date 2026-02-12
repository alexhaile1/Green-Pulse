package com.greenpulse.projectgreenpulse.service;

import com.greenpulse.projectgreenpulse.entities.Logistique;
import com.greenpulse.projectgreenpulse.entities.Message;
import com.greenpulse.projectgreenpulse.entities.Transaction;
import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.repos.MessageRepository;
import com.greenpulse.projectgreenpulse.repos.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Auteur : Alex */

@Service
public class MessageService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private MessageRepository messageRepository;

    public boolean envoyerMessageParEmail(Message message) {
        Utilisateur recepteur = utilisateurRepository.findByEmail(message.getEmailRecepteur());
        if (recepteur == null) {
            return false;
        }
        messageRepository.save(message);
        return true;
    }

    public List<Message> getMessagesPourEmail(String email) {
        return messageRepository.findByEmailRecepteur(email);
    }
}