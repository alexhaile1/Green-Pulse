package com.greenpulse.projectgreenpulse.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int acheteurId;

    @Column
    private int producteurId;

    @Column
    private int produitId;

    @Column(length = 128, nullable = false)
    private String nomGrain;

    @Column
    private BigDecimal quantiteAchetee;

    @Column
    private BigDecimal margeGP;

    @Column
    private BigDecimal prixTotal;

    @Column(length = 128, nullable = false)
    private String methodePaiement;

    @Column
    private BigDecimal montantPaiement;

    @Column(length = 128, nullable = false)
    private String statutPaiement;

    @Temporal(TemporalType.DATE)
    @Column(length = 128, nullable = false)
    private Date datePaiement;

    @Column(length = 128, nullable = false)
    private String statutCommande;

    @Column(length = 64, nullable = false)
    private String prenom;

    @Column(length = 64, nullable = false)
    private String nom;

    @Column(length = 128, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String adresse;

    @Column(length = 128, nullable = false)
    private String codePostal;

    @Column(length = 128, nullable = false)
    private String pays;

    @Column(length = 128, nullable = false)
    private String ville;

    @Column(length = 128, nullable = false)
    private String nomCarte;

    @Column
    private byte[] numeroCarteCredit;

    @Column
    private byte[] expiration;

    @Column
    private byte[] cvv;

    // Pour garder les produits en affichage
    @ManyToMany
    @JoinTable(
            name = "transactions_produits",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "produits_id")
    )
    private List<Produit> produits = new ArrayList<>();

    public Transaction() {
        this.produits = new ArrayList<>();
//        this.quantitesAchetees = new ArrayList<>();
//        this.prixNegocies = new ArrayList<>();
    }

    public Transaction(int id, int acheteurId, int producteurId, int produitId, String nomGrain,
                       BigDecimal quantiteAchetee, BigDecimal margeGP, BigDecimal prixTotal, Date dateTransaction,
                       String methodePaiement, BigDecimal montantPaiement, String statutPaiement, Date datePaiement,
                       String statutCommande, String prenom, String nom, String email, String adresse,
                       String codePostal, String pays, String ville, String nomCarte,
                       byte[] numeroCarteCredit, byte[] expiration, byte[] cvv) {

        this.id = id;
        this.acheteurId = acheteurId;
        this.producteurId = producteurId;
        this.produitId = produitId;
        this.nomGrain = nomGrain;
        this.quantiteAchetee = quantiteAchetee;
        this.margeGP = margeGP;
        this.prixTotal = prixTotal;
        this.methodePaiement = methodePaiement;
        this.montantPaiement = montantPaiement;
        this.statutPaiement = statutPaiement;
        this.datePaiement = datePaiement;
        this.statutCommande = statutCommande;

        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.pays = pays;
        this.ville = ville;
        this.nomCarte = nomCarte;
        this.numeroCarteCredit = numeroCarteCredit;
        this.expiration = expiration;
        this.cvv = cvv;

        /*this.produits = new ArrayList<>();
        this.quantitesAchetees = new ArrayList<>();
        this.prixNegocies = new ArrayList<>();*/
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcheteurId() {
        return acheteurId;
    }

    public void setAcheteurId(int acheteurId) {
        this.acheteurId = acheteurId;
    }

    public int getProducteurId() {
        return producteurId;
    }

    public void setProducteurId(int producteurId) {
        this.producteurId = producteurId;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public String getNomGrain() {
        return nomGrain;
    }

    public void setNomGrain(String nomGrain) {
        this.nomGrain = nomGrain;
    }

    public BigDecimal getQuantiteAchetee() {
        return quantiteAchetee;
    }

    public void setQuantiteAchetee(BigDecimal quantiteAchetee) {
        this.quantiteAchetee = quantiteAchetee;
    }

    public BigDecimal getMargeGP() {
        return margeGP;
    }

    public void setMargeGP(BigDecimal margeGP) {
        this.margeGP = margeGP;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    public BigDecimal getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(BigDecimal montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public byte[] getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    public void setNumeroCarteCredit(byte[] numeroCarteCredit) {
        this.numeroCarteCredit = numeroCarteCredit;
    }

    public byte[] getExpiration() {
        return expiration;
    }

    public void setExpiration(byte[] expiration) {
        this.expiration = expiration;
    }

    public byte[] getCvv() {
        return cvv;
    }


    /*public List<Float> getPrixNegocies() {
        return prixNegocies;
    }

    public void setPrixNegocies(List<Float> prixNegocies) {
        this.prixNegocies = prixNegocies;
    }*/

    /*public void ajouterProduit(Produit produit, int quantiteAchetee, float prixNegocie) {
        if (quantiteAchetee <= 0 || quantiteAchetee > produit.getQuantiteDisponible()) {
            System.out.println("La quantité est invalide pour " + produit.getNomGrain());
            return;
        }
        produits.add(produit);
        quantitesAchetees.add(quantiteAchetee);
        prixNegocies.add(prixNegocie);
        calculerPrixTotal();
        System.out.println("Produit ajouté à la transaction : " + produit.getNomGrain() + " (Quantité: " + quantiteAchetee + ", Prix négocié: " + prixNegocie + " $)");
    }*/

    /*private void calculerPrixTotal() {
        float total = 0;
        for (int i = 0; i < produits.size(); i++) {
            Produit produit = produits.get(i);
            int quantite = quantitesAchetees.get(i);
            float prixUnitaire = prixNegocies.get(i) != null ? prixNegocies.get(i) : produit.getPrixVente();
            total += prixUnitaire * quantite;
        }
        this.prixTotal = total + this.margeGP;
        System.out.println("Prix total recalculé : " + this.prixTotal + " $");
    }*/

    /*public void mettreAJourPrixTotal() {
        calculerPrixTotal();
    }*/

    public void setCvv(byte[] cvv) {
        this.cvv = cvv;
    }

    public String afficherColonnes() {
        return String.format(
                "%-5s %-12s %-12s %-12s %-15s %-12s %-10s %-10s %-20s %-20s %-15s %-15s %-15s %-15s %-15s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s",
                "ID", "AcheteurID", "ProducteurID", "ProduitID", "NomGrain", "Quantité", "MargeGP", "PrixTotal", "DateTransaction",
                "MéthodePaiement", "MontantPaiement", "StatutPaiement", "DatePaiement", "StatutCommande",
                "Prenom", "Nom", "Email", "Adresse", "CodePostal", "Pays", "Ville", "NomCarte", "NuméroCarte", "Expiration", "CVV"
        ) + "\n"
                + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
    }

    @Override
    public String toString() {
        return String.format(
                "%-5d %-12d %-12d %-12d %-15s %-12.2f %-10.2f %-10.2f %-20s %-20s %-15.2f %-15s %-15s %-15s %-15s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s",
                this.id, this.acheteurId, this.producteurId, this.produitId, this.nomGrain, this.quantiteAchetee,
                this.margeGP, this.prixTotal,
                this.methodePaiement, this.montantPaiement, this.statutPaiement,
                (this.datePaiement != null ? this.datePaiement.toString() : "N/A"), this.statutCommande,
                this.prenom, this.nom, this.email, this.adresse, this.codePostal, this.pays, this.ville,
                this.nomCarte,
                new String(this.numeroCarteCredit),
                new String(this.expiration),
                new String(this.cvv)
        );
    }
}