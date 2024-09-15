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

// Filters veterinarians by the clinic selected
document.getElementById('clinic').addEventListener('change', function() {
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

// hides veterinarian form until a clinic has been selected.
   var clinicSelect = document.getElementById('clinic');
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
