document.getElementById('reason').addEventListener('change', function() {
    var selectedServiceId = this.value;
    var prices = document.getElementById('prices');
    var priceItems = prices.querySelectorAll('li');

    // Show or hide pricing based on the selected service ID
    priceItems.forEach(function(item) {
        if (item.getAttribute('data-service-id') === selectedServiceId || selectedServiceId === "") {
            item.style.display = 'block'; // Show item if it matches or if no reason is selected
        } else {
            item.style.display = 'none'; // Hide item otherwise
        }
    });
});

function showNewPetModal() {
    $('#petModalContainer').load('/pets/register', function() {
        // Show the modal after loading
        $('#newPetModal').modal('show');
    });
}

function showExistingPets() {
    document.getElementById('existingPetsGrid').style.display = 'block'; // Show the existing pets dropdown
}

function nextStep(stepNumber) {
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
    document.getElementById('step-' + stepNumber).style.display = 'block';
}

function previousStep(stepNumber) {
    nextStep(stepNumber);
}

function handleSubmit(event) {
    event.preventDefault(); // Prevent the form from submitting normally
    const selection = document.getElementById('newOrExisting').value;
    let selectedPet = document.getElementById('petDropdown').value;

    if (selection === 'existing' && !selectedPet) {
        alert('Please select an existing pet.');
        return; // Exit if no existing pet is selected
    }

    if (selection === 'new') {
        const petName = document.getElementById('petName').value;
        const petType = document.getElementById('petType').value;
        const petAge = document.getElementById('petAge').value;
        if (!petName || !petType || !petAge) {
            alert('Please fill out all new pet details.');
            return; // Exit if new pet details are incomplete
        }
        console.log('New Pet Submitted:', { petName, petType, petAge });
    }

    // Gather other form data
    const reason = document.getElementById('reason').value;
    const clinic = document.querySelector('.pricing-box ul li').innerText;
    const appointmentDate = document.getElementById('appointment-date').value;
    const appointmentTime = document.getElementById('appointment-time').value;

    // Populate modal fields
    document.getElementById('confirmReason').innerText = reason;
    document.getElementById('confirmClinic').innerText = clinic;
    document.getElementById('confirmDate').innerText = appointmentDate;
    document.getElementById('confirmTime').innerText = appointmentTime;

    // Show the confirmation modal
    $('#confirmationModal').modal('show');
}
