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
    <title>Register Form</title>
</head>

<body>
<c:if test="${not empty check}">
	<script>
		alert("Mã BHYT đã có trong hệ thống!!!");
	</script>
	</c:if>
    <div class="container">
        <div class="forms-container">
            <div class="signin-signup">
                <form action="dangKyController" class="sign-in-form" method="POST" >
                    <h2 class="title">Đăng ký</h2>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" name="mabn" placeholder="Mã BHYT" required />
                    </div>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" name="hodem" placeholder="Họ đệm" required />
                    </div>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" name="tenbn" placeholder="Tên bệnh nhân" required />
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="password" id="passwordInput" placeholder="Password" required />
                        <i class="fas fa-eye" id="showPassword"></i>
                        <i class="fas fa-eye-slash" id="hidePassword" style="display:none;"></i>
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-cake-candles"></i>
                        <input type="text" name="ngaysinh" placeholder="Ngày sinh (dd/MM/yyyy)" required />
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-venus-mars"></i>
                        <input type="text" name="gioitinh" placeholder="Giới tính" required />
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-location-dot"></i>
                        <input type="text" name="diachi" placeholder="Địa chỉ" required />
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-phone"></i>
                        <input type="number" name="sodt" placeholder="Số điện thoại" required />
                    </div>
                    <input type="submit" name="dangky" value="Register" class="btn solid" />
                </form>
            </div>
        </div>

        <div class="panels-container">
            <div class="panel left-panel">
                <div class="content">
                    <h3>One of us ?</h3>
                    <p>
                        Already have an account?
                    </p>
                     <a class="btn transparent" href="dangNhapController">Login</a>
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