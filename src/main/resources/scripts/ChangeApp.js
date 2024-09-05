function nextChangeStep(stepNumber) {
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
    document.getElementById('change-step-' + stepNumber).style.display = 'block';
}

function previousChangeStep(stepNumber) {
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
    document.getElementById('change-step-' + stepNumber).style.display = 'block';
}

function handleChangeAction() {
    const action = document.getElementById('changeAction').value;
    if (action === 'reschedule') {
        nextChangeStep('reschedule');
    } else if (action === 'cancel') {
        cancelAppointment();
    }
}

function rescheduleAppointment() {
    const clinic = document.getElementById('clinic').value;
    const appointmentDate = document.getElementById('appointment-date').value;
    const appointmentTime = document.getElementById('appointment-time').value;
    const ownerName = document.getElementById('ownerName').value;
    const email = document.getElementById('email').value;

    if (!clinic || !appointmentDate || !appointmentTime || !ownerName || !email) {
        alert('Please fill in all the required fields.');
        return;
    }

    // Placeholder logic to simulate rescheduling
    console.log('Simulating reschedule with the following details:');
    console.log(`Clinic: ${clinic}, Date: ${appointmentDate}, Time: ${appointmentTime}, Owner: ${ownerName}, Email: ${email}`);

    // Fake confirmation message
    alert('Your appointment has been rescheduled. (no backend)');
}

function cancelAppointment() {
    const ownerName = document.getElementById('ownerName').value;
    const email = document.getElementById('email').value;

    if (!ownerName || !email) {
        alert('Please fill in all the required fields.');
        return;
    }

    console.log('Simulating cancellation with the following details:');
    console.log(`Owner: ${ownerName}, Email: ${email}`);

    // Fake confirmation message
    alert('Your appointment has been canceled. (no backend)');
}
