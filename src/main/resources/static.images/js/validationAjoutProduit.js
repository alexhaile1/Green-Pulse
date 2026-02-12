// Auteur : Hanfaoui Mariyam

$(document).ready( function(){
    $("#ajoutProduitForm").validate(
        {
            rules: {
                nomGrain: "required",
                typeGrain: "required",
                prixVente: "required",
                description: "required",
                productImage: "required",
                quantiteDisponible: {
                    required: true,
                    min: 1 // Optional: ensure it's greater than 0 if you want
                }
            },
            messages: {
                nomGrain: "Veuillez saisir le nom du produit",
                typeGrain: "Veuillez sélectionner le type du produit",
                prixVente: "Veuillez saisir le prix du produit",
                description: "Veuillez saisir la description du produit",
                quantiteDisponible: "Veuillez saisir la quantité disponible du produit",
                productImage: "Veuillez sélectionner une image pour le produit"
            },
            errorElement:"em"
        } );

    //La méthode on() attache un ou plusieurs gestionnaires d'événements
    //pour pour les éléments sélectionnés
    //ici on v disactive le bouton submit si le formulaire
    // n'est pas valide
    $('input').on('blur', function () {
        //verifi si le formulaire est valide
        if ($("#ajoutProduitForm").valid()) {
            // Activer le bouton submit : ajoutez propriété disabled a  false
            $('#submit').prop('disabled', false);
        } else {
            //Desactiver le bouton submit: met la proprieté disabled à disabled
            $('#submit').prop('disabled', 'disabled');
        }
    });
});