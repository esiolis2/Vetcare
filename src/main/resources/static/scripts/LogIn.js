function validateLogin() {
    const users = {
        "test@test.com": "test123",
        "ethan@test": "ethan1"
    };

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const loginError = document.getElementById("loginError");

    loginError.style.display = 'none';

    if (!users[email]) {
        loginError.textContent = "Error: Email is not registered.";
        loginError.style.display = 'block';
    } else if (users[email] !== password) {
        loginError.textContent = "Error: Incorrect password.";
        loginError.style.display = 'block';
    } else {
        const username = email.split('@')[0];
        localStorage.setItem('email', email);
        localStorage.setItem('username', username);
        localStorage.setItem('loggedIn', 'true');

        alert("Login successful!");
        window.location.href = "BookApp3.html";
    }
}
