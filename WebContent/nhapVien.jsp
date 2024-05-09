<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="stylehome.css">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Lịch sử nhâp viện</title>
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
        	<h1 class="content-title-info">THÔNG TIN NHẬP VIỆN</h1>
	         <c:choose>
            	<c:when test="${not empty admin}">
            	<form id="patientForm" action="nhapVienController" method="POST">
	                <select id="gender" name="txtbn" class="type_gender" style="width: 350px;">
					    <c:forEach var="bn" items="${bn}">
					        <option value="${bn.getMaBN()}" ${not empty nvsua && nvsua.getMaBN() eq bn.getMaBN() ? 'selected' : ''}>
					            ${bn.getMaBN()}
					            <span>(${bn.getHoDem()} ${bn.getTenBN()})</span>
					        </option>
					    </c:forEach>
					</select></br>
	                <input type="date" name="ngaynv" placeholder="Ngày nhập viện (dd/MM/yyyy)" value="${not empty nvsua ? nvsua.getNgayNV() : ''}" required  /></br>
	                <input type="date" name="ngayxv" placeholder="Ngày xuất viện (dd/MM/yyyy)" value="${not empty nvsua ? nvsua.getNgayXV() : ''}" required /></br>
	                <input type="text" name="txtcd" required placeholder="Chuẩn đoán" value="${not empty nvsua ? nvsua.getChuanDoan() : ''}" > </br>
	                <input type="text" name="txtts" required placeholder="Tiểu sử"  value="${not empty nvsua ? nvsua.getTieuSu() : ''}"> </br>
	                <input name="txtnv" type="hidden" value="${not empty nvsua ? nvsua.getMaNhapVien() : ''}">
	                <button type="submit" name="nutadd" class="btn-add">Thêm</button>
	                <button type="submit" name="nutupdate" class="btn-update">Lưu</button>
	            </form>
            <h1 class="content-title-list">DANH SÁCH NHẬP VIỆN</h1>
            <table class="table table-bordered ">
                <thead>
                    <tr>
                    	<th scope="col" style="padding-right: 14px">STT</th>
                    	<th scope="col" style="text-align: center;">Mã BHYT</th>
                        <th scope="col" style="width: 90px;text-align: center;">Ngày Nhập Viện</th>
                        <th scope="col" style="width: 90px;text-align: center;">Ngày Xuất Viện</th>
                        <th scope="col" style="text-align: center;">Chuẩn đoán</th>
                        <th scope="col">Tiểu sử</th>
                        <th scope="col" style="width: 84px;">Chức năng</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="nv" items="${dsnv}" varStatus="loop">
					<tr>
						<th scope="row" style="padding-right: 10px" >${loop.index + 1}</th>
						<td>${nv.getMaBN()}</td>
					    <td style="text-align: center;">${nv.getNgayNV()}</td>
					    <td style="text-align: center;">${nv.getNgayXV()}</td>
					    <td style="text-align: center;">${nv.getChuanDoan()}</td>
					    <td>${nv.getTieuSu()}</td>
					    <td>
                            <a href="nhapVienController?nvup=${nv.getMaNhapVien()}">
                            <i style="margin-left: 10px;
                                font-size: 20px;
                                margin-right: 10px;" class="ri-edit-fill"></i>
                            </a>
                            <a href="nhapVienController?nvxoa=${nv.getMaNhapVien()}">
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
            	<c:when test="${not empty user}">
            	<table class="table table-bordered ">
                <thead>
                    <tr>
                        <th scope="col" style="">Mgày Nhập Viện</th>
                        <th scope="col" style="">Ngày Xuất Viện</th>
                        <th scope="col" >Chuẩn đoán</th>
                        <th scope="col">Tiểu sử</th>
                        
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="nv" items="${dsnv}" varStatus="loop">
                <c:if test="${nv.getMaBN() eq user.getMaBN()}">
					<tr>
					    <td >${nv.getNgayNV()}</td>
					    <td >${nv.getNgayXV()}</td>
					    <td >${nv.getChuanDoan()}</td>
					    <td>${nv.getTieuSu()}</td>
					</tr>
					</c:if>
				</c:forEach>
                </tbody>
            </table>
            	</c:when>
            	<c:otherwise>
            	<script>
		        	alert("Vui lòng đăng nhập !!!!");
		        	window.location.href ='dangNhapController';
		        	</script>
            	</c:otherwise>
            </c:choose>
        </div>
    </section>
</body>

</html>