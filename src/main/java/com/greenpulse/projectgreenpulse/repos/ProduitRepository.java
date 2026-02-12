package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Auteurs : Yanis et Mariyam */

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

    // Méthode pour supprimer un produit
    public Long countById(Integer id);

    // Méthode pour trouver un produit par le nom
    @Query("SELECT p FROM Produit p WHERE p.nomGrain = :nom_grain")
    public Produit getProduitNom(@Param("nom_grain") String nomGrain);

    // Recherche par prix croissant
    @Query("SELECT p FROM Produit p ORDER BY p.prixVente DESC")
    List<Produit> findByOrdrePlusCher();

    // Recherche par prix décroissant
    @Query("SELECT p FROM Produit p ORDER BY p.prixVente ASC")
    List<Produit> findByOrdreMoinsCher();

    // Recherche par type de grain
    @Query("SELECT p.typeGrain, COUNT(p) FROM Produit p GROUP BY p.typeGrain")
    List<Object[]> produitParTypeGrain();

    // Recherche par prix de grain
    @Query("SELECT p.typeGrain, AVG(p.prixVente) FROM Produit p GROUP BY p.typeGrain")
    List<Object[]> produitParPrixVente();

    // Recherche d'un produit par son nom
    @Query("SELECT p FROM Produit p WHERE p.nomGrain LIKE %?1%")
    public List<Produit> findAll(String keyword);

    // Recherche d'un produit par l'email du producteur
    List<Produit> findByProducteurEmail(String producteurEmail);
}