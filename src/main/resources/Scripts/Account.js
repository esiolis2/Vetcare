// function fetchAccountDetails() {
//     document.getElementById('account-info-container').innerHTML = `
//         <p><strong>Email:</strong> testuser@example.com</p>
//         <p><strong>Phone Number:</strong> +123 456 7890</p>
//     `;
// }
//
// function fetchPetPrescriptions() {
//     document.getElementById('pet-prescriptions-container').innerHTML = `
//         <ul>
//             <li>Pet: testDog - Prescription: Flea Treatment</li>
//         </ul>
//     `;
// }
//
// function fetchPetDetails() {
//     document.getElementById('pet-details-container').innerHTML = `
//         <ul>
//             <li><strong>testDog:</strong> Age 3, Dog, Last Appointment: 15/09/2024</li>
//         </ul>
//     `;
// }
//
// document.getElementById('add-pet-button').addEventListener('click', function() {
//     const formContainer = document.getElementById('add-pet-form-container');
//     formContainer.style.display = formContainer.style.display === 'none' ? 'block' : 'none';
// });
//
// document.getElementById('add-pet-form').addEventListener('submit', function(e) {
//     e.preventDefault();
//
//     const petName = document.getElementById('petName').value;
//     const petType = document.getElementById('petType').value;
//     const petAge = document.getElementById('petAge').value;
//
//     setTimeout(function() {
//         document.getElementById('add-pet-form').reset();
//         document.getElementById('add-pet-form-container').style.display = 'none';
//
//         const petDetailsContainer = document.getElementById('pet-details-container');
//         petDetailsContainer.innerHTML += `<li><strong>${petName}:</strong> Age ${petAge}, ${petType}</li>`;
//
//         $('#confirmationModal').modal('show');
//     }, 500);
// });
//
// window.onload = function() {
//     fetchAccountDetails();
//     fetchPetPrescriptions();
//     fetchPetDetails();
// };
// Event listener for Add Pet form submission
document.getElementById('add-pet-form').addEventListener('submit', function (event) {
    event.preventDefault();  // Prevent form submission

    // Get values from the form inputs
    const petName = document.getElementById('petName').value;
    const petType = document.getElementById('petType').value;
    const petAge = document.getElementById('petAge').value;
    const petGender = document.getElementById('petGender').value;
    const petWeight = document.getElementById('petWeight').value;
    const petBreed = document.getElementById('petBreed').value;
    const petBirthDate = document.getElementById('petBirthDate').value;
    const ownerName = document.getElementById('ownerName').value;
    const ownerContact = document.getElementById('ownerContact').value;

    // Log the pet information for debugging purposes
    console.log(`New pet added: ${petName}, ${petType}, ${petAge}, ${petGender}, ${petWeight}, ${petBreed}, ${petBirthDate}, ${ownerName}, ${ownerContact}`);

    // Close the modal after submitting the form
    $('#addPetModal').modal('hide');

    // Display confirmation modal
    $('#confirmationModal').modal('show');
});
