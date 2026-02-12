// Auteur : Hanfaoui Mariyam

$(document).ready( function(){
    $("#paymentForm").validate(
        {
            rules: {
                prenom: "required",
                nom: "required",
                adresse: "required",
                codePostal: "required",
                pays: "required",
                ville: "required",
                nomCarte: "required",
                numeroCarteCredit: "required",
                expiration: "required",
                cvv: "required",
                courriel: {
                    required: true,
                    email: true
                },
            },
            messages: {
                prenom: "Veuillez saisir votre prenom",
                nom: "Veuillez saisir votre nom",
                adresse: "Veuillez saisir votre adresse",
                codePostal: "Veuillez saisir votre code postal",
                pays: "Veuillez saisir le pays que vous y situez",
                ville: "Veuillez saisir la ville d'habitation",
                nomCarte: "Veuillez saisir le nom de la personne ayant la carte",
                numeroCarteCredit: "Veuillez le numéro de la carte crédit",
                expiration: "Veuillez saisir la date d'expiration de la carte",
                cvv: "Veuillez saisir le numéro CVV de votre carte",
                courriel: { required : "Veuillez saisir votre email"
                }
            },
            errorElement:"em"
        } );

    //La méthode on() attache un ou plusieurs gestionnaires d'événements
    //pour pour les éléments sélectionnés
    //ici on v disactive le bouton submit si le formulaire
    // n'est pas valide
    $('input').on('blur', function () {
        //verifi si le formulaire est valide
        if ($("#paymentForm").valid()) {
            // Activer le bouton submit : ajoutez propriété disabled a  false
            $('#submit').prop('disabled', false);
        } else {
            //Desactiver le bouton submit: met la proprieté disabled à disabled
            $('#submit').prop('disabled', 'disabled');
        }
    });
});

//une méthode qui permet de verifier si le champ email correspond au pattern spécifié
jQuery.validator.addMethod(
    "email",
    function (courriel) {
        /*
        // // Fonction qui reçoit un courriel vérifie que ca commence par une lettre
    // que des lettres et des points et des tirets sans accents
    // (plus restrictif que la vraie spécification)

        */
        var patron = /^[a-z][a-z0-9]*((\.|\-)?[a-z0-9]+)*@([a-z0-9]+\.)+[a-z]+$/i;
        return patron.test(courriel.trim());

    },
    "Rentrez un email valide"
);