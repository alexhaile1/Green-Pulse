package com.greenpulse.projectgreenpulse.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/* Auteur : Leen */

@Controller
public class DeconnexionController {

    @GetMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        //fermer la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        //redirection avec un message
        redirectAttributes.addFlashAttribute("message", "Déconnexion réussite. À la prochaine!");
        return "redirect:/pageAccueil";
    }
}