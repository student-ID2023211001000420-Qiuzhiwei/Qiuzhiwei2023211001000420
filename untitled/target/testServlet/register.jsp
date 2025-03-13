<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#registrationForm').submit(function (event) {
                event.preventDefault();

                var username = $('#username').val();
                var password = $('#password').val();
                var email = $('#email').val();
                var birthdate = $('#birthdate').val();

                var isValid = true;

                // Validate username
                if (username === '') {
                    alert('Username is required');
                    isValid = false;
                }

                // Validate password
                if (password === '') {
                    alert('Password is required');
                    isValid = false;
                } else if (password.length < 6) {
                    alert('Password must be at least 6 characters long');
                    isValid = false;
                }

                // Validate email
                var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (email === '') {
                    alert('Email is required');
                    isValid = false;
                } else if (!emailRegex.test(email)) {
                    alert('Please enter a valid email address');
                    isValid = false;
                }

                // Validate birthdate
                var birthdateRegex = /^\d{4}-\d{2}-\d{2}$/;
                if (birthdate === '') {
                    alert('Birthdate is required');
                    isValid = false;
                } else if (!birthdateRegex.test(birthdate)) {
                    alert('Please enter a valid birthdate in yyyy-MM-dd format');
                    isValid = false;
                }

                if (isValid) {
                    this.submit();
                }
            });
        });
    </script>
</head>
<body>
<h2>User Registration</h2>
<form id="registrationForm" action="register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>

    <label for="email">Email:</label>
    <input type="text" id="email" name="email"><br>

    <label for="birthdate">Birthdate (yyyy-MM-dd):</label>
    <input type="date" id="birthdate" name="birthdate"><br>

    <label>Gender:</label>
    <input type="radio" id="male" name="gender" value="male">
    <label for="male">Male</label>
    <input type="radio" id="female" name="gender" value="female">
    <label for="female">Female</label><br>

    <input type="submit" value="Register">
</form>
</body>
</html>