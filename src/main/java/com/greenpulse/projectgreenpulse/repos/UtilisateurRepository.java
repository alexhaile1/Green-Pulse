package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Auteur : Mariyam, Leen, Yanis et Alex */

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    // Requête pour l'affichage des producteurs et des acheteurs en trouvant le type d'utilisateur
    public List<Utilisateur> findByTypeUtilisateur(Utilisateur.TypeUtilisateur typeUtilisateur);

    // Requête pour faire la mise a jour du status de l'utilisateur
    @Modifying
    @Query("UPDATE Utilisateur u SET u.status =?2 WHERE u.id= ?1")
    public void updateUtilisateur(Integer id, String status);

    // Méthode pour supprimer un produit
    public Long countById(Integer id);

    //chercher par courriel
    @Query("SELECT u FROM Utilisateur u WHERE u.email=:email")
    public Utilisateur getUtilisateurByEmail(@Param("email") String email);

    //chercher par courriel et MDP
    @Query("SELECT u FROM Utilisateur u WHERE u.email=:email and u.motDePasse=:mot_de_passe")
    public Utilisateur getUtilisateurByEmailAndPassword(@Param("email") String email, @Param("mot_de_passe") String password);

    //chercher tout - sans query car le query vient de JpaRepository
    public List<Utilisateur> findAll();

    //chercher par courriel
    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
    Utilisateur findByEmail(String email);

    //Recherche par type de compte
    List<Utilisateur> findByTypeUtilisateurAndStatus(Utilisateur.TypeUtilisateur typeUtilisateur, String status);
}