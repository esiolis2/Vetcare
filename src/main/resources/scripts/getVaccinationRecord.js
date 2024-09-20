function downloadPDF() {
       const { jsPDF } = window.jspdf;
       const doc = new jsPDF();
       doc.text("Pet Medical Records - Summary", 10, 10);
       doc.text("Pet Name: Fluffy", 10, 20);
       doc.text("Breed: Golden Retriever", 10, 30);
       doc.text("Date of Last Visit: 2023-08-15", 10, 40);
       doc.text("Last Diagnosis: Good health, no issues", 10, 50);
       doc.text("Vaccination Summary:", 10, 60);
       doc.text("Rabies: 2023-06-20 (Next Due: 2024-06-20)", 10, 70);
       doc.text("DHPP: 2023-04-12 (Next Due: 2024-04-12)", 10, 80);
       doc.text("Leptospirosis: 2023-07-10 (Next Due: 2024-07-10)", 10, 90);
       doc.text("Current Treatment Plan: Regular checkup, dental cleaning, and deworming", 10, 100);
       doc.save("pet-medical-records-summary.pdf");
   }
function shareRecords() {
       if (navigator.share) {
           navigator.share({
               title: 'Pet Medical Records Summary',
               text: 'Here is the medical records.',
               url: window.location.href
           }).then(() => {
               console.log('Records shared successfully');
               console.error('Error sharing records:', error);
           });
       } else {
           alert('Web Share API is not supported in this browser.');
       }
   }


function selectPet() {
   const petSelectElement = document.getElementById('petSelect');
   const petId = petSelectElement.value;
   if (petId && petId !== '') {
       window.location.href = `/vaccination?petId=${petId}`;
   } else {
       alert('Please select a valid pet from the list.');
   }
}

function scrollLeft() {
    const container = document.querySelector('.vaccination-records-row');
    const maxScrollLeft = 0;
    const currentScrollLeft = container.scrollLeft;

    if (currentScrollLeft > maxScrollLeft) {
        container.scrollBy({ left: -300, behavior: 'smooth' });
    }
}

function scrollRight() {
    const container = document.querySelector('.vaccination-records-row');
    const maxScrollLeft = container.scrollWidth - container.clientWidth;
    const currentScrollLeft = container.scrollLeft;

    if (currentScrollLeft < maxScrollLeft) {
        container.scrollBy({ left: 300, behavior: 'smooth' });
    }
}
