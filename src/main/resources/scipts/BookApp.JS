function nextStep(stepNumber) {
    document.querySelectorAll('.form-step').forEach(step => step.style.display = 'none');
    document.getElementById('step-' + stepNumber).style.display = 'block';
}

function previousStep(stepNumber) {
    nextStep(stepNumber);
}

function showNewPetModal() {
    $('#petModalContainer').load('/pets/register', function() {
        $('#newPetModal').modal('show');
    });
}

function showExistingPets() {
    document.getElementById('existingPetsGrid').style.display = 'block';
}

document.getElementById('reason').addEventListener('change', function() {
    var selectedServiceId = this.value;
    var prices = document.getElementById('prices');
    var priceItems = prices.querySelectorAll('li');

    priceItems.forEach(function(item) {
        if (item.getAttribute('data-service-id') === selectedServiceId || selectedServiceId === "") {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
});

document.getElementById('clinic').addEventListener('change', function() {
    var selectedClinicId = this.value;
    var veterinarianSelect = document.getElementById('vetId');
    var veterinarianOptions = veterinarianSelect.querySelectorAll('option');

    veterinarianOptions.forEach(function(option) {
        if (option.value === "placeholder" || option.getAttribute('data-clinic-id') === selectedClinicId) {
            option.style.display = 'block';
        } else {
            option.style.display = 'none';
        }
    });
});
