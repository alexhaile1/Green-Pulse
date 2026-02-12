package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/* Auteurs : Mariyam, Leen, Yanis et Alex */

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Retourne le portail du compte du producteur
    @GetMapping("/compteProducteur")
    public String compteProducteur() {
        return "compteProducteur";
    }

    // Retourne le portail du compte de l'acheteur
    @GetMapping("/compteAcheteur")
    public String compteAcheteur() {
        return "compteAcheteur";
    }

    // Retourne le portail du compte admin
    @GetMapping("/compteAdmin")
    public String compteAdmin() {
        return "compteAdmin";
    }

    // Retourne la page actualiteAgricole
//    @GetMapping("/actualiteAgricole")
//    public String actualiteAgricole() {
//        return "actualiteAgricole";
//    }

    // MÉTHODES DE ALEX
    // Affiche le formulaire de modification pour l'acheteur
    @GetMapping("/modifAcheteur")
    public String afficherFormModifAcheteur(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);

        if (utilisateur == null) {
            return "redirect:/erreur";
        }

        model.addAttribute("utilisateur", utilisateur);
        return "modifAcheteur";
    }

    // Enregistre les modifications par l'acheteur
    @PostMapping("/modifAcheteur")
    public String enregistrerModification(@ModelAttribute("utilisateur") Utilisateur utilisateurFormulaire, HttpSession session,RedirectAttributes redirectAttributes) {
        String emailSession = (String) session.getAttribute("email");
        Utilisateur utilisateurExistant = utilisateurService.getUtilisateurByEmail(emailSession);

        if (utilisateurExistant == null) {
            return "redirect:/erreur";
        }

        utilisateurExistant.setPrenom(utilisateurFormulaire.getPrenom());
        utilisateurExistant.setNom(utilisateurFormulaire.getNom());
        utilisateurExistant.setTelephone(utilisateurFormulaire.getTelephone());
        utilisateurExistant.setAdresse(utilisateurFormulaire.getAdresse());
        utilisateurExistant.setMotDePasse(utilisateurFormulaire.getMotDePasse());

        utilisateurService.mettreAJourUtilisateur(utilisateurExistant);

        session.setAttribute("prenom", utilisateurExistant.getPrenom());
        session.setAttribute("nom", utilisateurExistant.getNom());
        redirectAttributes.addFlashAttribute("message", "Le profil a été modifié avec succès!");

        return "redirect:/infoAcheteur";
    }

    // Affiche le formulaire de modification pour le producteur
    @GetMapping("/modifProducteur")
    public String afficherFormModifProducteur(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Utilisateur producteur = utilisateurService.getUtilisateurByEmail(email);

        if (producteur == null) {
            return "redirect:/erreur";
        }

        model.addAttribute("producteur", producteur);
        return "modifProducteur";
    }

    // Enregistre les modifications par le producteur
    @PostMapping("/modifProducteur")
    public String enregistrerModificationAcheteur(@ModelAttribute("producteur") Utilisateur producteurFormulaire, HttpSession session, RedirectAttributes redirectAttributes) {
        String emailSession = (String) session.getAttribute("email");
        Utilisateur utilisateurExistant = utilisateurService.getUtilisateurByEmail(emailSession);

        if (utilisateurExistant == null) {

            return "redirect:/erreur";
        }

        utilisateurExistant.setPrenom(producteurFormulaire.getPrenom());
        utilisateurExistant.setNom(producteurFormulaire.getNom());
        utilisateurExistant.setTelephone(producteurFormulaire.getTelephone());
        utilisateurExistant.setAdresse(producteurFormulaire.getAdresse());
        utilisateurExistant.setMotDePasse(producteurFormulaire.getMotDePasse());


        utilisateurService.mettreAJourUtilisateur(utilisateurExistant);

        session.setAttribute("prenom", utilisateurExistant.getPrenom());
        session.setAttribute("nom", utilisateurExistant.getNom());
        redirectAttributes.addFlashAttribute("message", "Le profil a été modifié avec succès!");

        return "redirect:/infoProducteur";
    }

    // MÉTHODES DE LEEN
    // Retourne les informations de l'admin
    @GetMapping("/infoAdmin")
    public String infoAdmin(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
            model.addAttribute("utilisateur", utilisateur);
            return "infoAdmin";
        }
        return "infoAdmin";
    }

    // Retourne les informations de l'acheteur
    @GetMapping("/infoAcheteur")
    public String infoAcheteur(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
            model.addAttribute("utilisateur", utilisateur);
            return "infoAcheteur";
        }
        return "infoAcheteur";
    }

    // Retourne les informations du producteur
    @GetMapping("/infoProducteur")
    public String infoProducteur(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
            model.addAttribute("utilisateur", utilisateur);
            return "infoProducteur";
        }
        return "infoProducteur";
    }

    // MÉTHODES DE MARIYAM
    // Retourne la page de gestion des acheteurs et des producteurs
    @GetMapping("/gestionAcheteurProducteur")
    public String gestionAcheteurProducteur(Model model) {
        List<Utilisateur> listeProducteurs = utilisateurService.afficherProducteurs();
        List<Utilisateur> listeAcheteurs = utilisateurService.afficherAcheteurs();
        model.addAttribute("listeProducteurs", listeProducteurs);
        model.addAttribute("listeAcheteurs", listeAcheteurs);
        return "gestionAcheteurProducteur";
    }

    // Méthode qui suspend l'utilisateur
    @GetMapping("/utilisateurs/{id}/{status}")
    public String mettreAJourStatusUtilisateur(@PathVariable("id") Integer id, @PathVariable("status") String status, Model model, RedirectAttributes redirectAttributes){
        utilisateurService.updateStatus(id, status);
        if(status.equals("active")){
            status = "suspendu";
        } else {
            status = "active";
        }
        redirectAttributes.addFlashAttribute("message", "Le status de l'utilisateur a été modifié avec succès!");
        return "redirect:/gestionAcheteurProducteur";
    }

    // Méthode qui supprime un utilisateur de la liste des utilisateurs
    @GetMapping("/utilisateurs/delete/{id}")
    public String supprimerUtilisateur(Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes){
        utilisateurService.supprimerUtilisateur(id);
        redirectAttributes.addFlashAttribute("message", "L'utilisateur a été supprimer avec succès!");
        return "redirect:/gestionAcheteurProducteur";
    }

    // MÉTHODES DE YANIS
    // Méthode qui affiche les informations d'un utilisateur de la liste des utilisateurs
    @GetMapping("/utilisateurs/info/{id}")
    public String voirInfoUtilisateur(@PathVariable("id") Integer id, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        model.addAttribute("utilisateur", utilisateur);
        return "voirInfoAdmin";
    }
}