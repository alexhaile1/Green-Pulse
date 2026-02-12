package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Transaction;
import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* Auteur : Alex et Mariyam */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Chercher les transactions avec la liste des produits
    @Query("SELECT DISTINCT t FROM Transaction t LEFT JOIN FETCH t.produits WHERE t.acheteurId = :acheteurId")
    List<Transaction> getTransactionsByAcheteurId(@Param("acheteurId") int acheteurId);

    List<Transaction> findByProducteurId(int producteurId);
}