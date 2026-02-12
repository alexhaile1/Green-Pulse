package com.greenpulse.projectgreenpulse.controller;


import org.hibernate.annotations.processing.HQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    // Retourne la page index
    @GetMapping("")
    public String pageAccueil (){
        return "index";
    }

    // Retourne la page d'accueil
    @GetMapping("/pageAccueil")
    public String accueil(){
        return "pageAccueil";
    }

    // Retourne la page des termes et conditions
    @GetMapping("/termsConditions")
    public String termsConditions(){
        return "termsConditions";
    }

    // Retourne la page d'histoire
    @GetMapping("/notreHistoire")
    public String notreHistoire(){
        return "notreHistoire";
    }

    // Retourne la page de contact général
    @GetMapping("/contacterNous")
    public String contactezNous(){
        return "contacterNous";
    }

}
