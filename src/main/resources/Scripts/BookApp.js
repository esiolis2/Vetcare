function validateStep(stepNumber) {
    let isValid = true;

    if (stepNumber === 1) {
        // Step 1 validation
        const clinic = document.getElementById('clinic').value;
        const appointmentDate = document.getElementById('appointment-date').value;
        const appointmentTime = document.getElementById('appointment-time').value;

        if (!clinic || !appointmentDate || !appointmentTime) {
            isValid = false;
            alert("Please fill in all fields before proceeding.");
        }
    } else if (stepNumber === 2) {
        // Step 2 validation
        const ownerName = document.getElementById('ownerName').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;

        if (!ownerName || !email || !phone) {
            isValid = false;
            alert("Please fill in all fields before proceeding.");
        }
    } else if (stepNumber === 3) {
        // Step 3 validation
        const newOrExisting = document.getElementById('newOrExisting').value;
        const petName = document.getElementById('petName').value;
        const petType = document.getElementById('petType').value;
        const petAge = document.getElementById('petAge').value;
        const petQuery = document.getElementById('petQuery').value;

        if (!newOrExisting || !petName || !petType || !petAge || !petQuery) {
            isValid = false;
            alert("Please fill in all fields before proceeding.");
        }
    }

    return isValid;
}

function nextStep(stepNumber) {
    if (validateStep(stepNumber - 1)) {
        document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
        document.getElementById('step-' + stepNumber).style.display = 'block';
    }
}

function previousStep(stepNumber) {
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
    document.getElementById('step-' + stepNumber).style.display = 'block';
}


function bookAppointment() {
    const clinic = document.getElementById('clinic').value;
    const petName = document.getElementById('petName').value;
    const petType = document.getElementById('petType').value;
    const petAge = document.getElementById('petAge').value;
    const ownerName = document.getElementById('ownerName').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const appointmentDate = document.getElementById('appointment-date').value;
    const appointmentTime = document.getElementById('appointment-time').value;
    const petQuery = document.getElementById('petQuery').value;
    const bookingNumber = Math.floor(10000000 + Math.random() * 90000000);


    document.getElementById('confirmationModal').querySelector('.modal-body').innerHTML += `<p><strong>Booking Number:</strong> ${bookingNumber}</p>`;
    document.getElementById('confirmClinic').innerText = clinic;
    document.getElementById('confirmPetName').innerText = petName;
    document.getElementById('confirmPetType').innerText = petType;
    document.getElementById('confirmPetAge').innerText = petAge;
    document.getElementById('confirmOwnerName').innerText = ownerName;
    document.getElementById('confirmEmail').innerText = email;
    document.getElementById('confirmPhone').innerText = phone;
    document.getElementById('confirmDate').innerText = appointmentDate;
    document.getElementById('confirmTime').innerText = appointmentTime;
    document.getElementById('confirmReason').innerText = petQuery;


    $('#confirmationModal').modal('show');
}


