// Auteur : Hanfaoui Mariyam

$(document).ready( function(){
    $("#registrationForm").validate(
        {
            rules: {
                prenom: "required",
                nom: "required",
                telephone: "required",
                pays: "required",
                adresse: "required",
                typeUtilisateur : "required", // instead of 'role'

                typeCulture: {
                    required: function () {
                        return $("#role").val() === "Producteur";
                    }
                },
                superficieExploitée: {
                    required: function () {
                        return $("#role").val() === "Producteur";
                    }
                },
                typeEntreprise: {
                    required: function () {
                        return $("#role").val() === "Acheteur";
                    }
                },
                volumeAchatMensuel: {
                    required: function () {
                        return $("#role").val() === "Acheteur";
                    }
                },
                raisonSociale: {
                    required: function () {
                        return $("#role").val() === "Acheteur";
                    }
                },
                motDePasse: {
                    required: true,
                    minlength: 5,
                    password: true
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#motdepasse"
                },
                email: {
                    required: true,
                    email: true
                },
            },
            messages: {
                prenom: "Veuillez saisir votre prenom",
                nom: "Veuillez saisir votre nom",
                telephone: "Veuillez saisir votre numéro de téléphone",
                pays: "Veuillez saisir le pays que vous y situez",
                adresse: "Veuillez saisir votre adresse",
                role : "Veuillez sélectionner un rôle",
                typeCulture: "Veuillez saisir votre type de culture",
                superficieExploitée: "Veuillez saisir la superficie que vous avez exploitée",
                typeEntreprise: "Veuillez saisir le type d'entreprise de votre entreprise",
                volumeAchatMensuel: "Veuillez saisir le volume d'achat mensuel",
                raisonSociale: "Veuillez saisir le nom de votre entreprise",
                password: {
                    required: "Veuillez saisir votre mot de passe",
                    minlength: "Votre mot de passe doit contenir au moins 5 characters"
                },
                confirm_password: {
                    required: "Veuillez saisir votre mot de passe",
                    minlength: "Votre identifiant doit contenir au moins 5 characters",
                    equalTo: "Veuillez saisir le même mot de passe que précédemment"
                },
                email: { required : "Veuillez saisir votre email"
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
        if ($("#registrationForm").valid()) {
            // Activer le bouton submit : ajoutez propriété disabled a  false
            $('#submit').prop('disabled', false);
        } else {
            //Desactiver le bouton submit: met la proprieté disabled à disabled
            $('#submit').prop('disabled', 'disabled');
        }
    });
});


function toggleRoleFields() {
    var role = document.getElementById("role").value;
    var producteurFields = document.getElementById("producteurFields");
    var acheteurFields = document.getElementById("acheteurFields");

    if (role === "Producteur") {
        producteurFields.style.display = "block";
        acheteurFields.style.display = "none";
    } else if (role === "Acheteur") {
        acheteurFields.style.display = "block";
        producteurFields.style.display = "none";
    } else {
        acheteurFields.style.display = "none";
        producteurFields.style.display = "none";
    }
}


//une méthode qui permet de verifier si le champ mot de passe correspond au pattern spécifié
jQuery.validator.addMethod(
    "password",
    function(value){
        return  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,}$/.test(value) ;
    },
    'Le mot de passe doit contenir : au moins une lettre minuscule, au moins une lettre majuscule, au moins un chiffre, au moins cinq caractères'
);


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