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

$(document).ready(function() {
    $('#appointmentDate').datepicker({
        startDate: new Date(),
        format: 'yyyy-mm-dd',
        daysOfWeekDisabled: [0, 6],
        autoclose: true,
        todayHighlight: true
    });
});

 $(document).ready(function() {
        $('#appointment-time').timepicker({
            timeFormat: 'h:i a',
            minTime: '9:00am',
            maxTime: '6:00pm',
            interval: 30
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
