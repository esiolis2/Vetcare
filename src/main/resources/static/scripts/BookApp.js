function showPricing() {
    const reason = document.getElementById('petQuery').value;
    const pricingBox = document.getElementById('pricingBox');
    if (reason) {
        pricingBox.style.display = 'block'; // Show the pricing box
    } else {
        pricingBox.style.display = 'none';  // Hide the pricing box if no reason is selected
    }
}

function validateStep(stepNumber) {
    // Get the current step
    const currentStep = document.getElementById('step-' + stepNumber);

    // Check for required fields in the current step
    const requiredFields = currentStep.querySelectorAll('[required]');
    for (let field of requiredFields) {
        if (!field.value || field.value === 'placeholder') {
            alert('Please fill all required fields.');
            return false; // Return false if any required field is empty
        }
    }

    return true; // All required fields are filled
}

function nextStep(stepNumber) {
    // Validate the current step before proceeding
    if (!validateStep(stepNumber - 1)) {
        return; // Stop if validation fails
    }

    // Hide all steps
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');

    // Show the next step
    document.getElementById('step-' + stepNumber).style.display = 'block';
}

function previousStep(stepNumber) {
    nextStep(stepNumber);
}


let selectedPet = null;

function selectPet(button, petName) {
    // Reset all button styles
    document.querySelectorAll('.pet-button').forEach(btn => {
        btn.classList.remove('btn-primary');
        btn.classList.add('btn-outline-primary');
    });

    // Highlight the selected button
    button.classList.remove('btn-outline-primary');
    button.classList.add('btn-primary');

    // Store the selected pet
    selectedPet = petName;
    console.log('Selected Pet:', selectedPet);
}

function togglePetSelection() {
    const selection = document.getElementById('newOrExisting').value;
    const existingPetsGrid = document.getElementById('existingPetsGrid');
    const newPetForm = document.getElementById('newPetForm');

    if (selection === 'existing') {
        existingPetsGrid.style.display = 'block'; // Show existing pets grid
        newPetForm.style.display = 'none'; // Hide new pet form
    } else if (selection === 'new') {
        existingPetsGrid.style.display = 'none'; // Hide existing pets grid
        newPetForm.style.display = 'block'; // Show new pet form
    } else {
        existingPetsGrid.style.display = 'none'; // Hide both if no selection
        newPetForm.style.display = 'none';
    }
}

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
document.getElementById('reason').addEventListener('change', function() {
    var selectedServiceId = this.value;
    var prices = document.getElementById('prices');
    var priceItems = prices.querySelectorAll('li');
    var pricingBox = document.getElementById("pricingBox");

    var hasVisibleItems = false;

    // Show or hide pricing based on the selected service ID
    priceItems.forEach(function(item) {
        if (item.getAttribute('data-service-id') === selectedServiceId || selectedServiceId === "") {
            item.style.display = 'block'; // Show item if it matches or if no reason is selected
            hasVisibleItems = true;
        } else {
            item.style.display = 'none'; // Hide item otherwise
        }
    });

    // Show the pricing box if there are visible items, otherwise hide it
    if (hasVisibleItems) {
        pricingBox.style.display = 'block';
    } else {
        pricingBox.style.display = 'none';
    }
});

function showNewPetModal() {
    $('#petModalContainer').load('/pets/register', function() {
        $('#addPetModal').modal('show');
    });
}

function showExistingPets() {
    document.getElementById('existingPetsGrid').style.display = 'block';
}



