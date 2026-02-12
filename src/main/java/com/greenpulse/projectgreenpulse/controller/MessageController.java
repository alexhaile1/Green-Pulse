package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.Message;
import com.greenpulse.projectgreenpulse.entities.Transaction;
import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.repos.UtilisateurRepository;
import com.greenpulse.projectgreenpulse.service.MessageService;
import com.greenpulse.projectgreenpulse.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.greenpulse.projectgreenpulse.entities.Utilisateur.TypeUtilisateur;


import java.util.Date;
import java.util.List;

/* Auteur : Alex */

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Producteur voit ces message
    @GetMapping("/messageProducteur")
    public String messageProducteur(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/connexion";

        List<Message> messages = messageService.getMessagesPourEmail(email);
        model.addAttribute("messages", messages);
        return "messageProducteur";
    }

    //Achteur voit ces message
    @GetMapping("/messageAcheteur")
    public String messageAcheteur(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/connexion";

        List<Message> messages = messageService.getMessagesPourEmail(email);
        model.addAttribute("messages", messages);
        return "messageAcheteur";
    }

    //Affiche le formulaire pour contacter un Producteur
    @GetMapping("/contacterProducteur")
    public String afficherFormulaireProducteur(Model model) {
        model.addAttribute("message", new Message());

        List<Utilisateur> acheteurs = utilisateurRepository.findByTypeUtilisateurAndStatus(TypeUtilisateur.Acheteur, "active");

        model.addAttribute("acheteurs", acheteurs);
        return "contacterAcheteur";
    }

    // Gérer la soumission du formulaire
    @PostMapping("/contact-acheteur")
    public String envoyerMessageAcheteur(@ModelAttribute("message") Message message, Model model, HttpSession session) {
        String emailConnecte = (String) session.getAttribute("email");

        message.setEmailEnvoyeur(emailConnecte);

        message.setDateEnvoi(new Date());
        boolean vrai = messageService.envoyerMessageParEmail(message);

        if (!vrai) {
            model.addAttribute("erreur", "L'email de l'acheteur n'existe pas");
            return "contacterAcheteur";
        }

        model.addAttribute("envoyer", "Le message est envoyé");
        model.addAttribute("message", new Message());
        return "contacterAcheteur";
    }


    //Affiche le formulaire pour contacter un Acheteur
    @GetMapping("/contacterAcheteur")
    public String afficherFormulaire(Model model) {
        model.addAttribute("message", new Message());

        List<Utilisateur> producteurs = utilisateurRepository.findByTypeUtilisateurAndStatus(Utilisateur.TypeUtilisateur.Producteur, "active");

        model.addAttribute("producteurs", producteurs);
        return "contacterProducteur";
    }


    // Gérer la soumission du formulaire
    @PostMapping("/contact-producteur")
    public String envoyerMessage(@ModelAttribute("message") Message message, HttpSession session, Model model) {
        String emailEnvoyeur = (String) session.getAttribute("email");
        if (emailEnvoyeur == null) return "redirect:/connexion";

        message.setEmailEnvoyeur(emailEnvoyeur);
        message.setDateEnvoi(new Date());

        boolean estValide = messageService.envoyerMessageParEmail(message);

        if (!estValide) {
            model.addAttribute("erreur", "L’email du producteur n'existe pas");
            return "contacterProducteur";
        }

        model.addAttribute("envoyer", "Le message est envoyé");
        model.addAttribute("message", new Message());
        return "contacterProducteur";
    }
}