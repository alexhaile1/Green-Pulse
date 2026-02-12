package com.greenpulse.projectgreenpulse.controller;

import com.greenpulse.projectgreenpulse.entities.*;
import com.greenpulse.projectgreenpulse.service.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.*;

/* Auteur : Alex et Mariyam */

@Controller
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private LogistiqueService logistiqueService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PanierService panierService;
    @Autowired
    private ProduitService produitService;

    // Affiche la page du formulaire de paiement
    @GetMapping("/paiement")
    public String afficherFormulairePaiement() {
        return "paiement";
    }

    // Traite le formulaire de paiement et crée une nouvelle transaction
    @PostMapping("/paiement")
    public String creerTransaction(HttpSession session , Model model, RedirectAttributes redirectAttributes, @RequestParam String prenom, @RequestParam String nom, @RequestParam String email, @RequestParam String adresse, @RequestParam String codePostal, @RequestParam String pays, @RequestParam String ville, @RequestParam String nomCarte, @RequestParam String numeroCarteCredit, @RequestParam String expiration, @RequestParam String cvv) {

        String acheteurEmail = (String) session.getAttribute("email");
        Utilisateur acheteur = utilisateurService.getUtilisateurByEmail(acheteurEmail);

        // Récupérer la session panier pour afficher les informations nécéssaire
        Panier panier = panierService.obtenirPanier(session);

        if (acheteur == null){
            return "redirect:/connexion";
        }

        if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || adresse.isEmpty()
                || codePostal.isEmpty() || pays.isEmpty() || ville.isEmpty()
                || nomCarte.isEmpty() || numeroCarteCredit.isEmpty() || expiration.isEmpty() || cvv.isEmpty()) {

            model.addAttribute("erreur", "Veuillez saisir tous les champs");
            return "paiement";
        }

        Transaction transaction = new Transaction();

        transaction.setAcheteurId(acheteur.getId());
        transaction.setPrenom(prenom);
        transaction.setNom(nom);
        transaction.setEmail(email);
        transaction.setAdresse(adresse);
        transaction.setCodePostal(codePostal);
        transaction.setPays(pays);
        transaction.setVille(ville);

        transaction.setNomCarte(nomCarte);
        transaction.setNumeroCarteCredit(numeroCarteCredit.getBytes());
        transaction.setExpiration(expiration.getBytes());
        transaction.setCvv(cvv.getBytes());

        // Liste de produits à partir de la transaction
        List<Produit> produits = new ArrayList<>();
        // Initialisation de la quantité et des prix
        double totalQuantite = 0;
        BigDecimal totalPrix = new BigDecimal(0);

        // Récupérer les informations du panier et les mettre dans la transaction
        for(Produit produit : panier.getListePanier()){
            transaction.setNomGrain(produit.getNomGrain());
            produits.add(produit);
            double quantity = panier.getQuantitePourProduit(produit);
            totalQuantite = quantity;
            totalPrix = produit.getPrixVente().multiply(BigDecimal.valueOf(quantity));
        }

        // Mettre la liste des produits dans la transaction
        transaction.setProduits(produits);

        transaction.setQuantiteAchetee(BigDecimal.valueOf(totalQuantite));
        transaction.setPrixTotal(totalPrix);
        transaction.setMontantPaiement(BigDecimal.valueOf(panier.getPrixTotal()));
        transaction.setMethodePaiement("Carte de crédit");
        transaction.setStatutPaiement("Payé");
        transaction.setStatutCommande("Confirmée");
        transaction.setDatePaiement(new Date());

        transactionService.save(transaction);

        redirectAttributes.addFlashAttribute("message", "Transaction effectué! Veuillez voir vos messages pour avoir le numéro du suivi de votre commande.");

        // Génération de valeurs aléatoires pour chaque attribut
        long logistiqueId = (long)(Math.random() * 100000);
        int delai = (int)(Math.random() * 100);
        BigDecimal fraisTransport = BigDecimal.valueOf(Math.random() * 500);
        Random r = new Random();

        List<String> listeStatut = Arrays.asList("Délai", "En transit", "Livré");
        int randomString = r.nextInt(listeStatut.size());
        String statut = listeStatut.get(randomString);

        List<String> listeTransport = Arrays.asList("DHL", "UPS", "Purolator", "Maritime", "Aérien", "Routier", "Ferroviaire");
        int randomString2 = r.nextInt(listeTransport.size());
        String transport = listeTransport.get(randomString2);

        // Création d'un logistique à partir de la transaction à la suite d'un paiement
        Logistique logistique = new Logistique();
        logistique.setId(logistiqueId);
        logistique.setTransactionId(transaction.getId());
        logistique.setDelai(delai);
        logistique.setFraisTransport(fraisTransport);
        logistique.setTransporteur(transport);
        logistique.setStatutLivraison(statut);

        logistiqueService.enregistrer(logistique);

        // Création d'un message dans le portail de l'acheteur afin qu'il puisse connaitre son numéro de suivi
        Message message = new Message();
        int messageId = (int)(Math.random() * 100000);

        message.setId(messageId);
        message.setDateEnvoi(new Date());
        message.setEmailEnvoyeur("greenpulse.support@gmail.ca");
        message.setEmailRecepteur(acheteurEmail);
        message.setContenuMessage("Voici votre numéro de commande : " + logistiqueId);

        messageService.envoyerMessageParEmail(message);

        // Vider le panier complètement
        panier.viderPanier();

        return "redirect:/historiqueAcheteur";
    }

    // Affiche l'historique des commandes de l'acheteur
    @GetMapping("/historiqueAcheteur")
    public String afficherHistorique(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/connexion";
        }
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
        if (utilisateur == null) {
            return "redirect:/connexion";
        }

        int acheteurId = utilisateur.getId();
        List<Transaction> listeTransactions = transactionService.getTransactionsByAcheteurId(acheteurId);

        model.addAttribute("listeTransactions", listeTransactions);
        return "historiqueAcheteur";
    }

    // Affiche l'historique des ventes du producteur
    @GetMapping("/historiqueVenteProducteur")
    public String afficherHistoriqueProducteur(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/connexion";
        }

        Utilisateur producteur = utilisateurService.getUtilisateurByEmail(email);
        if (producteur == null) {
            return "redirect:/connexion";
        }

        int producteurId = producteur.getId();
        List<Transaction> listeTransactions = transactionService.getTransactionsByProducteurId(producteurId);

        model.addAttribute("listeTransactions", listeTransactions);
        return "historiqueVenteProducteur";
    }


}