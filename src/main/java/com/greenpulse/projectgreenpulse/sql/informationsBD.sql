/* Insertion des données dans la table des utilisateurs */
INSERT INTO `greenpulsedb`.`utilisateurs`
(`id`, `type_utilisateur`,
 `nom`,
 `prenom`,
 `raison_sociale`,
 `email`,
 `telephone`,
 `adresse`,
 `pays`,
 `mot_de_passe`,
 `status`,
 `type_culture`,
 `superficie_exploitée`,
 `certification_bio`,
 `type_entreprise`,
 `volume_achat_mensuel`)
VALUES
    (1,'Producteur','Abdellah','Ahmed',null,'abdellah@gmail.ca','514 098-3765', '2011, 13e avenue', 'Canada', 'Ahmed123', 'active', 'Agriculture', 100.0, true, null, null),
    (2,'Acheteur','LaTulipe','Delphine','Legumineuses','latulipe@gmail.ca','438 698-2105', '84515, 16e avenue', 'Canada', 'Delphine456', 'active', null, null, false, 'Organisme', '100000'),
    (3,'Administrateur','Simons','David',null,'simons@gmail.ca','514 985-0000', '1765, rue Brunet', 'Canada', 'Simons827', 'active',null, null, false, null, null),
    (4,'Producteur','Choi','Mikasa',null,'choi@gmail.ca','438 007-3094', '1234, 19e avenue', 'Canada', 'Choi9184', 'active', 'Agriculture', 225.9, true, null, null),
    (5,'Acheteur','Trudeau','Martin','Vegetable.Co','trudeau@gmail.ca','514-200-2020', '5482, 58e avenue', 'Canada', 'TrudeauMart135', 'active',null, null, false, 'Organisme', '255841'),
    (6,'Administrateur','Aalaoui','Boutaina',null,'aalaoui@gmail.ca','438-458-5126', '6104, 2e avenue', 'Canada', 'Boutaina9175', 'active', null, null, false, null, null),
    (7,'Producteur','Boisvert','Jean',null,'boisvert@gmail.ca','438-741-8529', '8877, 53 avenue', 'Canada', 'Boisvert8754', 'active', 'Agriculture', 9851.5, true, null, null),
    (8,'Acheteur','Vilaire','Sarah','Cereal Studios','vilaire@gmail.ca','514-654-9874', '9999, 64e avenue', 'Canada', 'Vilaire098', 'active', null, null, false, 'Organisme', '698754'),
    (9,'Administrateur','McNeil','Chris',null,'mcneil@gmail.ca','438-000-1111', '2000, 20e avenue', 'Canada', 'McNeil1097', 'active', null, null, false, null, null);

/* Insertion des données dans la table des produits */
INSERT INTO `greenpulsedb`.`produits`
(`id`,
 `nom_grain`,
 `type_grain`,
 `producteur_email`,
 `quantite_disponible`,
 `prix_vente`,
 `date_publication`,
 `description`)
VALUES
    (1,'Blé','Céréales', "choi@gmail.ca", 500, 25.8,'2025-01-06', "Le blé est l'une des céréales les plus cultivées au monde. Il est utilisé principalement pour la production de farine."),
    (2,'Pois chiche','Légumineuse', "abdellah@gmail.ca", 875, 56.8, '2025-01-06', "Le pois chiche est une légumineuse riche en protéines et en fibres. Il est un ingrédient clé dans de nombreuses cuisines à travers le monde."),
    (3,'Amande','Oléagineux', "boisvert@gmail.ca", 200, 21.5, '2025-01-06', "L'amande est une graine provenant du fruit de l'amandier. Elle est riche en vitamines, minéraux et graisses saines."),
    (4,'Avoine','Céréales', "choi@gmail.ca", 1000, 98.7, '2025-01-06', "L'avoine est une céréale qui est souvent utilisée pour la préparation de petits déjeuners sains comme les flocons d''avoine."),
    (5,'Haricot','Légumineuse', "abdellah@gmail.ca", 2500, 65.8, '2025-01-06', "Le haricot est une légumineuse consommée dans de nombreux plats traditionnels à travers le monde."),
    (6,'Pistache','Oléagineux', "boisvert@gmail.ca", 687, 65.8, '2025-01-06', " La pistache est un oléagineux savoureux et nutritif. Elle est riche en graisses saines, en fibres et en antioxydants."),
    (7,'Orge','Céréales', "choi@gmail.ca", 548, 47.2, '2025-01-06', "L'orge est une céréale polyvalente utilisée dans la fabrication de pain, de soupes et de boissons comme la bière."),
    (8,'Mais','Légumineuse', "abdellah@gmail.ca", 2036, 58.4, '2025-01-06', "Le maïs est une légumineuse de base dans de nombreuses cultures. Il est utilisé sous différentes formes, telles que le maïs soufflé, les tortillas et les farines."),
    (9, 'Cajou', 'Oléagineux', "boisvert@gmail.ca", 222, 29.1, '2025-04-19', "Un oléagineux nutritif au goût doux et beurré. Le cajou est riche en bonnes graisses."),
    (10, 'Riz', 'Céréales', "choi@gmail.ca", 779, 45.2, '2025-04-19', "Le riz est polyvalent, facile à cuisiner, et constitue une excellente source de glucides essentiels pour l'énergie quotidienne."),
    (11, 'Céleri', 'Légumineuse', "abdellah@gmail.ca", 1214, 87.4, '2025-04-19', "Le céleri est parfait pour des en-cas sains, des salades ou pour ajouter du croquant aux plats."),
    (12, 'Macadamia', 'Oléagineux', "boisvert@gmail.ca", 5148, 57.1, '2025-04-19', "La noix de macadamia est une gourmandise raffinée, consommée seule ou utilisée en pâtisserie.");

/* Insertion des données dans la table transactions */
INSERT INTO `greenpulsedb`.`transactions`
(`acheteur_id`,
 `producteur_id`,
 `produit_id`,
 `nom_grain`,
 `quantite_achetee`,
 `margegp`,
 `prix_total`,
 `methode_paiement`,
 `montant_paiement`,
 `statut_paiement`,
 `statut_commande`,
 `prenom`,
 `nom`,
 `email`,
 `adresse`,
 `code_postal`,
 `pays`,
 `ville`,
 `nom_carte`,
 `numero_carte_credit`,
 `expiration`,
 `cvv`,
 `date_paiement`)
VALUES
    (2, 1, 1, 'Blé', 100, 5.00, 2550.00, 'carte_credit', 2550.00, 'validé', 'confirmée', 'Delphine', 'LaTulipe', 'latulipe@gmail.ca', '84515, 16e avenue', 'H2X 1Y2', 'Canada', 'Montréal', 'Delphine LaTulipe', AES_ENCRYPT('1234567812345678', 'key'), AES_ENCRYPT('12/27', 'key'), AES_ENCRYPT('123', 'key'), '2025-04-02');

/* Insertion des données dans la table logistique */
INSERT INTO greenpulsedb.transactions (
    acheteur_id, adresse, code_postal, cvv, date_paiement, email, expiration, margegp,
    methode_paiement, montant_paiement, nom, nom_carte, nom_grain, numero_carte_credit,
    pays, prenom, prix_total, producteur_id, produit_id, quantite_achetee,
    statut_commande, statut_paiement, ville
)
VALUES
    (
        2, '84515, 16e avenue', 'H2X 1X1', AES_ENCRYPT('123', 'secret_key'), '2024-04-22', 'latulipe@gmail.ca',
        AES_ENCRYPT('12/26', 'secret_key'), 15.75, 'Carte de crédit', 5000.00, 'Haile', 'Alex Haile',
        'Pois chiche', AES_ENCRYPT('4111111111111111', 'secret_key'), 'Canada', 'Alex',
        5750.00, 1, 101, 500.00, 'expédiée', 'payée', 'Montréal'
    ),
    (
        5, '5482, 58e avenue', 'H3Z 2B4', AES_ENCRYPT('456', 'secret_key'), '2024-04-15', 'trudeau@gmail.ca',
        AES_ENCRYPT('07/25', 'secret_key'), 10.00, 'Carte débit', 10000.00, 'Trudeau', 'Martin Trudeau',
        'Carottes', AES_ENCRYPT('5555444433332222', 'secret_key'), 'Canada', 'Martin',
        11000.00, 4, 102, 2000.00, 'en cours', 'en attente', 'Laval'
    ),
    (
        8, '9999, 64e avenue', 'J4B 2C2', AES_ENCRYPT('789', 'secret_key'), '2024-04-18', 'vilaire@gmail.ca',
        AES_ENCRYPT('09/27', 'secret_key'), 20.00, 'Virement bancaire', 25000.00, 'Vilaire', 'Sarah Vilaire',
        'Blé dur', AES_ENCRYPT('6011122233334444', 'secret_key'), 'Canada', 'Sarah',
        30000.00, 7, 103, 2500.00, 'livrée', 'payée', 'Québec'
    );

INSERT INTO greenpulsedb.transactions_produits (transaction_id, produits_id)
VALUES (1, 1), (1, 2), (2, 1);

INSERT INTO greenpulsedb.logistique (id,
                                     delai, frais_transport, statut_livraison, transaction_id, transporteur
)
VALUES
    (154, 5, 150.00, 'livré', 1, 'DHL'),
    (175, 7, 250.00, 'en transit', 2, 'Purolator'),
    (34, 3, 300.00, 'livré', 3, 'UPS');