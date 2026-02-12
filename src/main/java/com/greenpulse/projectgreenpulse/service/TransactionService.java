package com.greenpulse.projectgreenpulse.service;

import com.greenpulse.projectgreenpulse.entities.Transaction;
import com.greenpulse.projectgreenpulse.repos.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/* Auteur : Alex */

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getTransactionsByAcheteurId(int acheteurId) {
        return repository.getTransactionsByAcheteurId(acheteurId);
    }

    public List<Transaction> getTransactionsByProducteurId(int producteurId) {
        return repository.findByProducteurId(producteurId);
    }

    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    public List<Transaction> getVentesParProducteurId(int producteurId) {
        return repository.findByProducteurId(producteurId);
    }
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }
}