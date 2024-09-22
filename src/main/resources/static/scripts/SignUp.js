// Function to handle the form submission
function handleSignup() {
    const name = document.getElementById('name').value;
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Update confirmation message
    document.getElementById('confirmName').innerText = name;
    document.getElementById('confirmUsername').innerText = username;
    document.getElementById('confirmEmail').innerText = email;

    // Show confirmation modal
    $('#confirmationModal').modal('show');

    // Prevent the default form submission
    event.preventDefault();


}

// Add event listener for form submission
document.getElementById('signupForm').addEventListener('submit', handleSignup);
