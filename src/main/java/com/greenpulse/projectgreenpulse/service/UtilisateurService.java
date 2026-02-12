package com.greenpulse.projectgreenpulse.service;

import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import com.greenpulse.projectgreenpulse.repos.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/* Auteur : Mariyam, Leen, Yanis et Alex */

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // MÉTHODES DE MARIYAM
    // Affichage des producteurs dans la page gestionAcheteurProducteur
    public List<Utilisateur> afficherProducteurs() {
        return utilisateurRepository.findByTypeUtilisateur(Utilisateur.TypeUtilisateur.Producteur);
    }

    // Affichage des acheteurs dans la page gestionAcheteurProducteur
    public List<Utilisateur> afficherAcheteurs(){
        return utilisateurRepository.findByTypeUtilisateur(Utilisateur.TypeUtilisateur.Acheteur);
    }

    // Mise à jour du status de l'utilisateur dans la page gestionAcheteurProducteur
    public void updateStatus(Integer id, String status) {
        utilisateurRepository.updateUtilisateur(id, status);
    }

    // Méthode pour supprimer un utilisateur
    public void supprimerUtilisateur(Integer id) {
        Long countById = utilisateurRepository.countById(id);
        //pas d'utilisateur dans la bd
        if (countById == null || countById == 0) {
            System.out.println("Erreur : l'utilisateur n'existe pas");
        }
        utilisateurRepository.deleteById(id);
    }


    // MÉTHODES DE LEEN
    //On cherche un utilisateur à partir de son email
    public Utilisateur rechercherUtilisateurParEmailMotDePasse(String email, String password) {
        Utilisateur userByEmailAndPassword = utilisateurRepository.getUtilisateurByEmailAndPassword(email, password);
        if (userByEmailAndPassword == null) {
            return null;
        }
        return userByEmailAndPassword;
    }

    public List<Utilisateur> findAllUtilisateurs() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur enregistrer(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }


    // MÉTHODES DE ALEX
    public String getEmailById(int id) {
        return utilisateurRepository.findById(id)
                .map(Utilisateur::getEmail)
                .orElse("Inconnu");
    }

    public void mettreAJourUtilisateur(Utilisateur utilisateurModifie) {
        Utilisateur existant = utilisateurRepository.findByEmail(utilisateurModifie.getEmail());

        if (existant != null) {
            existant.setPrenom(utilisateurModifie.getPrenom());
            existant.setNom(utilisateurModifie.getNom());
            existant.setTelephone(utilisateurModifie.getTelephone());
            existant.setAdresse(utilisateurModifie.getAdresse());
            existant.setMotDePasse(utilisateurModifie.getMotDePasse());
            utilisateurRepository.save(existant);
        }
    }

    // MÉTHODES DE YANIS
    public Utilisateur getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id).orElse(null);
    }
}