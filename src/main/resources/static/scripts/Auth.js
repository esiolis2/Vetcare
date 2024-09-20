document.addEventListener('DOMContentLoaded', function() {
    const loggedIn = localStorage.getItem('loggedIn');
    const username = localStorage.getItem('username');

    const loginLink = document.getElementById('loginLink');
    const signupLink = document.getElementById('signupLink');
    const signedInAs = document.getElementById('signedInAs');
    const signOutButton = document.getElementById('signOutButton');
    const usernameDisplay = document.getElementById('usernameDisplay');

    if (loginLink && signupLink && signedInAs && signOutButton && usernameDisplay) {
        if (loggedIn === 'true' && username) {
            loginLink.style.display = 'none';
            signupLink.style.display = 'none';
            signedInAs.style.display = 'block';
            signOutButton.style.display = 'block';
            usernameDisplay.textContent = username;
        } else {
            loginLink.style.display = 'block';
            signupLink.style.display = 'block';
            signedInAs.style.display = 'none';
            signOutButton.style.display = 'none';
        }
    } else {
        console.error("Navbar elements are missing from the DOM. Ensure the IDs match.");
    }
});

function signOut() {
    localStorage.clear();
    window.location.reload();
}
