function fetchAccountDetails() {
    const email = localStorage.getItem('email');
    const username = localStorage.getItem('username');

    if (email && username) {
        document.getElementById('account-info-container').innerHTML = `
            <p><strong>Email:</strong> ${email}</p>
            <p><strong>Username:</strong> ${username}</p>
        `;
    }
}


function fetchPetPrescriptions() {
    document.getElementById('pet-prescriptions-container').innerHTML = `
        <ul>
            <li>Pet: testDog - Prescription: Flea Treatment</li>
        </ul>
    `;
}

function fetchPetDetails() {
    document.getElementById('pet-details-container').innerHTML = `
        <ul>
            <li><strong>testDog:</strong> Age 3, Dog, Last Appointment: 15/09/2024</li>
        </ul>
    `;
}

document.getElementById('add-pet-button').addEventListener('click', function() {
    const formContainer = document.getElementById('add-pet-form-container');
    formContainer.style.display = formContainer.style.display === 'none' ? 'block' : 'none';
});

document.getElementById('add-pet-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const petName = document.getElementById('petName').value;
    const petType = document.getElementById('petType').value;
    const petAge = document.getElementById('petAge').value;

    setTimeout(function() {
        document.getElementById('add-pet-form').reset();
        document.getElementById('add-pet-form-container').style.display = 'none';

        const petDetailsContainer = document.getElementById('pet-details-container');
        petDetailsContainer.innerHTML += `<li><strong>${petName}:</strong> Age ${petAge}, ${petType}</li>`;

        $('#confirmationModal').modal('show');
    }, 500);
});

window.onload = function() {
    fetchAccountDetails();
    fetchPetPrescriptions();
    fetchPetDetails();
};
