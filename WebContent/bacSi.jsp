<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.net.URLDecoder, java.net.URLEncoder, java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="stylehome.css">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Quản lý bệnh nhân</title>
</head>

<body>
	<%
	request.setCharacterEncoding("UTF-8");//gửi lên sever bằng mã utf-8
	response.setCharacterEncoding("UTF-8");//trả về client bằng mã utf-8
	%>
	<c:if test="${not empty check}">
		<script>
		alert("Mã bác sĩ đã có trong hệ thống !!!")
		</script>
		
	</c:if>
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
                <li>
                    <a href="bacSiController">
                        <span class="icon icon-2"><i class="ri-user-2-line"></i></span>
                        <span class="sidebar--item" style="white-space: nowrap">Bác sĩ</span>
                    </a>
                </li>
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
                    <h2>Welcome ${dn.getTenBN()}</h2>
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
        <c:choose>
            <c:when test="${not empty admin}">
        	<h1 class="content-title-info">THÔNG TIN BÁC SĨ</h1>
	            <form id="patientForm" action="bacSiController" method="POST" enctype="multipart/form-data">
	                <input type="text" name="txtmabs" required placeholder="Mã bác sĩ" value="${not empty upbs ? upbs.getMaBacSi() : ''}" ${not empty upbs ? 'readonly' : ''} ></br>
	
	                <input type="text" name="txttenbs" required placeholder="Tên bác sĩ" value="${not empty upbs ? upbs.getTenBacSi() : ''}"></br>
	
	                <input type="text"  name="txtnganh" required placeholder="Ngành" value="${not empty upbs ? upbs.getNganh() : ''}"></br>
	                <input type="file" name="txtfile" > </br>
	                <button type="submit" name="nutadd" class="btn-add">Thêm</button>
	                <button type="submit" name="nutupdate" class="btn-update">Lưu</button>
	            </form>
            <h1 class="content-title-list">DANH SÁCH BÁC SĨ</h1>
            <table class="table">
                <thead>
                    <tr style="">
                    	<th scope="col">STT</th>
                        <th scope="col">Mã Bác Sĩ</th>
                        <th scope="col">Tên Bác Sĩ</th>
                        <th scope="col">Ngành</th>
                        <th scope="col">Ảnh</th>
                        <th scope="col">Chức năng</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="bs" items="${dsbs}" varStatus="loop">
					<tr>
						<th scope="row">${loop.index + 1}</th>
					   <td>${bs.getMaBacSi()}</td>
					   <td>${URLDecoder.decode(bs.getTenBacSi(), 'UTF-8')}</td>
					   <td>${URLDecoder.decode(bs.getNganh(), 'UTF-8')}</td>
					   <td>${bs.getAnhBacSi()}</td>
					   <td>
                            <a href="bacSiController?mbsup=${bs.getMaBacSi()}&tenbs=${bs.getTenBacSi()}&nganh=${bs.getNganh()}">
                            <i style="margin-left: 10px;
                                font-size: 20px;
                                margin-right: 10px;" class="ri-edit-fill"></i>
                            </a>
                            <a href="bacSiController?mbsxoa=${bs.getMaBacSi()}">
                            <i style="margin-left: 10px;
                                font-size: 20px;
                                margin-right: 10px;" class="ri-delete-bin-line"></i>
                            </a>
                        </td>
					</tr>
				</c:forEach>
                </tbody>
            </table>
            </c:when>
            <c:otherwise>
            <script>
            alert("Vui lòng đăng nhập !!!");
            window.location.href ='dangNhapController';
            </script>
            </c:otherwise>
            </c:choose>
        </div>
    </section>
</body>

</html>