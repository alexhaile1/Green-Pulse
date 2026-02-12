package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testCreerTransactionAvecOrdreBD() {
        Transaction transaction = new Transaction();


        transaction.setAcheteurId(5);
        transaction.setAdresse("5482, 58e avenue");
        transaction.setCodePostal("75000");
        transaction.setCvv("123".getBytes());
        transaction.setDatePaiement(new Date());
        transaction.setEmail("trudeau@gmail.ca");
        transaction.setExpiration("12/25".getBytes());
        transaction.setMargeGP(new BigDecimal("10.00"));
        transaction.setMethodePaiement("carte crédit");
        transaction.setMontantPaiement(new BigDecimal("110.00"));
        transaction.setNom("Trudeau");
        transaction.setNomCarte("AMINE KOUASSI");
        transaction.setNomGrain("Riz Bio");
        transaction.setNumeroCarteCredit("4111111111111111".getBytes());
        transaction.setPays("France");
        transaction.setPrenom("Amine");
        transaction.setPrixTotal(new BigDecimal("110.00"));
        transaction.setProducteurId(transaction.getProducteurId());
        transaction.setProduitId(1);
        transaction.setQuantiteAchetee(new BigDecimal("100.00"));
        transaction.setStatutCommande("confirmée");
        transaction.setStatutPaiement("validé");
        transaction.setVille("Paris");

        Transaction savedTransaction = transactionRepository.save(transaction);

        assertNotNull(savedTransaction.getId());
        System.out.println("Transaction enregistrée avec ID : " + savedTransaction.getId());
    }
}
