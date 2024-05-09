<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.net.URLDecoder, java.net.URLEncoder, java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="stylehome.css">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Trang chủ</title>
</head>

<body>
    <section class="header">
        <a href="trangChuController" class="logo">
            <i class="ri-menu-line icon icon-0 menu"></i>
            <h2>MedEx</h2>
        </a>
    </section>
    <section class="main">
        <div class="sidebar">
            <ul class="sidebar--items">
                <li>
                    <a href="benhNhanController" id="">
                        <span class="icon icon-1"><i class="ri-user-line"></i></span>
                        <span class="sidebar--item">Bệnh nhân</span>
                    </a>
                </li>
                <c:if test="${not empty admin}">
                 <li>
                    <a href="bacSiController">
                        <span class="icon icon-2"><i class="ri-user-2-line"></i></span>
                        <span class="sidebar--item" style="white-space: nowrap">Bác sĩ</span>
                    </a>
                </li>
                </c:if>
                <li>
                    <a href="nhapVienController">
                        <span class="icon icon-3"><i class="fa-solid fa-hospital"></i></span>
                        <span class="sidebar--item">Nhập viện</span>
                    </a>
                </li>
                <li>
                    <a href="xetNghiemController">
                        <span class="icon icon-4"><i class="fa-solid fa-flask-vial"></i></span>
                        <span class="sidebar--item">Xét nghiệm</span>
                    </a>
                </li>
                <li>
                    <a href="donThuocController">
                        <span class="icon icon-5"><i class="fa-solid fa-stethoscope"></i></span>
                        <span class="sidebar--item">Đơn thuốc</span>
                    </a>
                </li>
            </ul>
            <ul class="sidebar--bottom-items">
            <c:choose>
            	<c:when test="${not empty admin}">
                <li>
                    <h2>Welcome Admin</h2>
                </li>
                <li>
                    <a href="dangXuatController">
                        <span class="icon icon-8"><i class="ri-logout-box-r-line"></i></span>
                        <span class="sidebar--item">Logout</span>
                    </a>
                </li>
                </c:when>
            	<c:when test="${not empty user}">
                <li>
                    <h2>Welcome ${user.getTenBN()}</h2>
                </li>
                <li>
                    <a href="dangXuatController">
                        <span class="icon icon-8"><i class="ri-logout-box-r-line"></i></span>
                        <span class="sidebar--item">Logout</span>
                    </a>
                </li>
                </c:when>
                <c:otherwise>
                <li>
                    <a href="dangNhapController">
                        <span class="icon icon-8"><i class="ri-login-box-line"></i></span>
                        <span class="sidebar--item">Đăng nhập</span>
                    </a>
                </li>
                <li>
                    <a href="dangKyController">
                        <span class="icon icon-8"><i class="ri-user-line"></i></span>
                        <span class="sidebar--item">Đăng ký</span>
                    </a>
                </li>
                </c:otherwise>
             </c:choose>
            </ul>
        </div>
        <div class="main--content">
            <div class="overview">
                <div class="title">
                    <h2 class="section--title">Overview</h2>
                </div>
                <div class="cards">
	                <div class="card card-1">
	                        <div class="card--data">
	                            <div class="card--content">
	                                <h5 class="card--title">Bệnh nhân</h5>
	                                <h1>${slbn}</h1>
	                            </div>
	                            <i class="ri-user-line card--icon--lg"></i>
	                        </div>
	                        <c:if test="${not empty admin}">
	                        <div class="card--stats">
	                        	<span><i class="fa-solid fa-people-roof card--icon "></i>${slgd}</span>
	                        	<span><i class="fa-solid fa-child card--icon down--arrow"></i>${slte}</span>
	                        	<span><i class="fa-solid fa-school card--icon up--arrow"></i>${slhs}</span>
	                            <span><i class="fa-solid fa-graduation-cap card--icon stat--icon"></i>${slsv}</span>						
	                        </div>
	                        </c:if>
	                    </div>
                    <div class="card card-2">
                        <div class="card--data">
                            <div class="card--content">
                                <h5 class="card--title">Bác sĩ</h5>
                                <h1>${slbs}</h1>
                            </div>
                            <i class="ri-user-2-line card--icon--lg"></i>
                        </div>
                        </div>
                    <div class="card card-3">
                        <div class="card--data">
                            <div class="card--content">
                                <h5 class="card--title">Xét nghiệm</h5>
                                <h1>${slxn}</h1>
                            </div>
                            <i class="fa-solid fa-vial-circle-check card--icon--lg"></i>
                            
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="doctors">
                <div class="title">
                    <h2 class="section--title">Doctors</h2>
                </div>
			    <div class="doctors--cards">
			        <c:forEach var="ds" items="${ttbs}" varStatus="loopStatus">
			            <c:if test="${loopStatus.index < 6}">
			                <a href="#" class="doctor--card">
			                    <div class="img--box--cover">
			                        <div class="img--box">
			                            <img src="${ds.getAnhBacSi()}" alt="">
			                        </div>
			                    </div>
			                    <p class="free">${URLDecoder.decode(ds.getTenBacSi(), 'UTF-8')}</p>
			                </a>
			            </c:if>
			        </c:forEach>
			    </div>
            </div>
        </div>
        
    </section>
    <script src="assets/js/main.js"></script>
</body>

</html>