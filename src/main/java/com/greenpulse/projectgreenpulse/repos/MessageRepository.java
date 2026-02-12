package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Message;
import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Auteur : Alex */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByEmailRecepteur(String emailRecepteur);
}