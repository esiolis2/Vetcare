function nextStep(stepNumber) {
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
    document.getElementById('step-' + stepNumber).style.display = 'block';
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

    // Update confirmation message
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
