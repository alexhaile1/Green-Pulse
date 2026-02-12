// Méthode d'exportation en PDF
function exportToPDF(event) {
    event.preventDefault();

    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.setFontSize(18);
    doc.text('Facture - ' + new Date().toLocaleDateString(), 15, 15);

    // Section des détails du client
    doc.setFontSize(12);

    const formData = new FormData(document.getElementById('paymentForm'));
    let yPosition = 55;

    doc.text(`Nom: ${formData.get('prenom')} ${formData.get('nom')}`, 15, yPosition);
    yPosition += 10;
    doc.text(`Email: ${formData.get('email')}`, 15, yPosition);
    yPosition += 10;
    doc.text(`Adresse: ${formData.get('adresse')}`, 15, yPosition);
    yPosition += 10;
    doc.text(`Ville: ${formData.get('ville')}, ${formData.get('codePostal')}`, 15, yPosition);
    yPosition += 10;
    doc.text(`Pays: ${formData.get('pays')}`, 15, yPosition);
    yPosition += 15;

    // Section de paiement
    doc.setFontSize(14);
    doc.text('Informations de Paiement:', 15, yPosition);
    yPosition += 10;

    // Détail sur le paiement
    doc.setFontSize(12);
    const numeroCarteCredit = formData.get('numeroCarteCredit');
    const numeroCarteCreditCacher = '**** **** **** ' + numeroCarteCredit.slice(-4);
    doc.text(`Carte: ${numeroCarteCreditCacher}`, 15, yPosition);
    yPosition += 10;
    doc.text(`Expiration: ${formData.get('expiration')}`, 15, yPosition);
    yPosition += 15;

    doc.save('facture_' + new Date().toISOString().slice(0,10) + '.pdf');

    // Pour faire la soumission du formulaire puisque le type du bouton n'est plus un submit
    const form = document.getElementById('paymentForm');
    form.submit();
}