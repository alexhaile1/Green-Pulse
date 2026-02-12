// Auteur : Hanfaoui Mariyam
// Vidéo qui m'as aidé : https://www.youtube.com/watch?v=GXbYQOykr_I

(function() {
    // https://dashboard.emailjs.com/admin/account
    emailjs.init({
        publicKey: "h-EPq7pi02mVzzEsv",
    });
})();

let emailEnvoyeur = document.getElementById('emailEnvoyeur')
let messageForm = document.getElementById('contenuMessage')

window.onload = function() {
    document.getElementById('contactForm').addEventListener('submit', function(event) {
        event.preventDefault();
        // these IDs from the previous steps
        emailjs.sendForm('service_oul1iff', 'template_6fe9x26', this).then(
            function(){
                document.getElementById('contactForm').reset();
                emailEnvoyeur.innerHTML = "";
                messageForm.innerHTML = "";
                Swal.fire({
                    title: "Votre message a été envoyé avec succès!",
                    icon: "Votre message a été envoyé avec succès!" === "success",
                    confirmButtonText: "Ok",
                    confirmButtonColor: "#000000",
                    background: "#013c01",
                    color: "#ffffff"
                });
            },
            function(error){
                Swal.fire({
                    title: "Votre message n'a pas été envoyé avec succès",
                    icon: "Votre message n'a pas été envoyé avec succès" === "error",
                    confirmButtonText: "Ok",
                    confirmButtonColor: "#000000",
                    background: "#013c01",
                    color: "#ffffff"
                });
            }
        );
    });
}