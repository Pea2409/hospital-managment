<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="styleform.css" />
    <title>Login Form</title>
</head>
<body>
	<c:if test ="${not empty check}">
		<script>
		alert("Vui lòng kiểm tra lại thông tin!!!");
		</script>
	</c:if>
    <div class="container sign-up-mode">
        <div class="forms-container">
            <div class="signin-signup">
                <form action="dangNhapController" class="sign-up-form" method="POST">
                    <h2 class="title">Login</h2>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" name="user" placeholder="Username" />
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="pass" id="passwordInput" placeholder="Password" required />
                        <i class="fas fa-eye" id="showPassword"></i>
                        <i class="fas fa-eye-slash" id="hidePassword" style="display:none;"></i>
                    </div>
                    <div class="input-field">
                    	<i class="fa-solid fa-shield-halved"></i>
						<input type="text" name="answer" placeholder="Captcha code" /><br>
                    </div>
                    <div>
						<img src="simpleCaptcha.jpg" />
                    </div>
                    
                    <input type="submit" class="btn" value="Login" />
                </form>
            </div>
        </div>

        <div class="panels-container">
            <div class="panel left-panel">
                <div class="content">
                    <h3>New here ?</h3>
                    <p>
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Debitis,
                        ex ratione. Aliquid!
                    </p>
                    <button class="btn transparent">
                        Sign up
                    </button>
                </div>
                <img src="hospital.png" class="image" alt="" />
            </div>
            <div class="panel right-panel">
                <div class="content">
                    <h3>One of us ?</h3>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum
                        laboriosam ad deleniti.
                    </p>
                    <a class="btn transparent" href="dangKyController">Register</a>
                </div>
                <img src="hospital.png" class="image" alt="" />
            </div>
        </div>
    </div>

    
    
    <script>
	
        const passwordInput = document.getElementById('passwordInput');
        const showPassword = document.getElementById('showPassword');
        const hidePassword = document.getElementById('hidePassword');

        showPassword.addEventListener('click', function () {
            passwordInput.setAttribute('type', 'text');
            showPassword.style.display = 'none';
            hidePassword.style.display = 'inline-block';
        });

        hidePassword.addEventListener('click', function () {
            passwordInput.setAttribute('type', 'password');
            hidePassword.style.display = 'none';
            showPassword.style.display = 'inline-block';
        });
    </script>
</body>

</html>