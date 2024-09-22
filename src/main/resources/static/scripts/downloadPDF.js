// File: /scripts/downloadPDF.js

function downloadPDF(recordNumber, recordName, petDetails, treatmentPlanNumber, vaccinationName, clinicName, vetName, date) {
    // Initialize jsPDF
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    // Set the title of the PDF
    doc.setFontSize(18);
    doc.text('Medical Record Details', 20, 20);

    // Add the details
    doc.setFontSize(12);
    doc.text(`Record Number: ${recordNumber}`, 20, 30);
    doc.text(`Record Name: ${recordName}`, 20, 40);
    doc.text(`Pet Details: ${petDetails}`, 20, 50);
    doc.text(`Treatment Plan Number: ${treatmentPlanNumber}`, 20, 60);
    doc.text(`Vaccination Name: ${vaccinationName}`, 20, 70);
    doc.text(`Clinic Name: ${clinicName}`, 20, 80);
    doc.text(`Veterinarian Name: ${vetName}`, 20, 90);
    doc.text(`Date: ${date}`, 20, 100);

    // Save the generated PDF
    doc.save(`Medical_Record_${recordNumber}.pdf`);
}
