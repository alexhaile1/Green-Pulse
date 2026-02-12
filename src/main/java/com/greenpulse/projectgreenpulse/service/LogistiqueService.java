package com.greenpulse.projectgreenpulse.service;

import com.greenpulse.projectgreenpulse.entities.Logistique;
import com.greenpulse.projectgreenpulse.entities.Transaction;
import com.greenpulse.projectgreenpulse.repos.LogistiqueRepository;
import org.springframework.stereotype.Service;

/* Auteur : Alex et Mariyam */

@Service
public class LogistiqueService {
    private final LogistiqueRepository repository;

    public LogistiqueService(LogistiqueRepository repository) {
        this.repository = repository;
    }

    public Logistique chercherParId(long id) {
        return repository.findById(id).orElse(null);
    }

    public Logistique enregistrer(Logistique logistique) { return repository.save(logistique); }
}