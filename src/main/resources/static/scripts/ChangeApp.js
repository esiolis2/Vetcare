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


document.getElementById('id').addEventListener('change', function() {
    var selectedClinicId = this.value;
    var veterinarianSelect = document.getElementById('vetId');
    var veterinarianOptions = veterinarianSelect.querySelectorAll('option');

    veterinarianOptions.forEach(function(option) {
        if (option.value === "placeholder" ||option.value === "" || option.getAttribute('data-clinic-id') === selectedClinicId) {
            option.style.display = 'block';
        } else {
            option.style.display = 'none';
        }
    });

});


 var clinicSelect = document.getElementById('id');
                var veterinarianForm = document.getElementById('veterinarianForm');

                // Add event listener to clinic select
                clinicSelect.addEventListener('change', function() {
                    // Check if a valid clinic is selected
                    if (clinicSelect.value) {
                        // Show veterinarian form if a clinic is selected
                        veterinarianForm.style.display = 'block';
                    } else {
                        // Hide veterinarian form if "Select a Clinic" is chosen
                        veterinarianForm.style.display = 'none';
                    }
                });