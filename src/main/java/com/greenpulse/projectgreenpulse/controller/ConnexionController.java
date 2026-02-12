package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.service.UtilisateurService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.Duration;

/* Auteur : Leen */

@Controller
public class ConnexionController {

    @Autowired
    private UtilisateurService userService;

    //Retourne le form d'inscription
    @GetMapping("/inscription/new")
    public String ajouterUtilisateur(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        return "inscription";
    }

    @PostMapping("/inscription/save")
    public String enregistrerUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur, Model model, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "inscription";
        }

        //verifier si le courriel existe deja
        Utilisateur courrielExistant = userService.getUtilisateurByEmail(utilisateur.getEmail());
        if (courrielExistant != null) {
            String erreur = "Ce courriel est déjà utilisé.";
            //afficher l'erreur si le courriel existe deja
            model.addAttribute("erreur", erreur);
            return "inscription";
        }

        utilisateur.setStatus("active"); //status=active automatiquement
        userService.enregistrer(utilisateur);
        model.addAttribute("utilisateur", utilisateur);
        redirectAttributes.addFlashAttribute("message", "Le compte a été crée!");

        return "redirect:/connexion"; //pour se connecter
    }

    //Retourne le form de connexion
    @GetMapping("/connexion")
    public String connexion(Model model){
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        return "connexion";
    }

    //méthode pour verification de la connexion
    @PostMapping("/connexion/verification")
    public String verifierConnexion(Model model, RedirectAttributes redirectAttributes, @RequestParam("email") String email, @RequestParam("motDePasse") String password, HttpServletRequest request, HttpServletResponse response) {
        Utilisateur utilisateur = userService.rechercherUtilisateurParEmailMotDePasse(email, password);
        //debugging pour voir si l'utilisateur = null
        System.out.println(utilisateur);

        //verifier si l'utilisateur est suspendu et si l'utilisateur existe on cree un session et un cookie
        if (utilisateur != null) {

            //si l'utilisateur est suspendu, il ne va pas pouvoir se connecter
            if (utilisateur.getStatus().equals("suspendu")) {
                redirectAttributes.addFlashAttribute("message", "Votre compte est suspendu. Veuillez contacter le support.");
                return "redirect:/connexion";
            }

            //créer et ajouter sessions&cookies
            HttpSession session = request.getSession(true);
            session.setAttribute("nom", utilisateur.getNom());
            session.setAttribute("prenom", utilisateur.getPrenom());
            session.setAttribute("email", utilisateur.getEmail());

            Cookie emailCookie = new Cookie("email", email);
            Cookie mdpCookie = new Cookie("password", password);
            emailCookie.setMaxAge(Duration.ofDays(60 * 60).getNano());
            mdpCookie.setMaxAge(Duration.ofDays(60 * 60).getNano());
            response.addCookie(emailCookie);
            response.addCookie(mdpCookie);
            model.addAttribute("utilisateur", utilisateur);
            System.out.println("session : " + session.getAttribute("nom"));

            //redireger l'utilisateur selon le type d'utilisateur (3types)
            Utilisateur.TypeUtilisateur userType = utilisateur.getTypeUtilisateur();
            if (userType != null) {
                switch (userType) {
                    case Administrateur:
                        return "redirect:/compteAdmin";
                    case Acheteur:
                        return "redirect:/compteAcheteur";
                    case Producteur:
                        return "redirect:/compteProducteur";
                }
            }
            return "redirect:/utilisateurs";

        } else {
            //si l'utilisateur n'a pas bien saisie les donnnees
            redirectAttributes.addFlashAttribute("message", "L'email ou le mot de passe est invalide ");
            return "redirect:/connexion";
        }
    }
}