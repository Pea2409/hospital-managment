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
    <title>Xét nghiệm</title>
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
        	<h1 class="content-title-info">THÔNG TIN XÉT NGHIỆM</h1>
	        <c:choose>
            	<c:when test="${not empty admin}">
            		<form id="patientForm" action="xetNghiemController" method="POST">
	                <select id="gender" name="txtbn" class="type_gender" style="width: 350px;">
					    <c:forEach var="bn" items="${bn}">
					        <option value="${bn.getMaBN()}" ${not empty sua && sua.getMaBn() eq bn.getMaBN() ? 'selected' : ''}>
					            ${bn.getMaBN()}
					            <span>(${bn.getHoDem()} ${bn.getTenBN()})</span>
					        </option>
					    </c:forEach>
					</select></br>
	                <input type="date" name="ngayth" placeholder="Ngày nhập viện (dd/MM/yyyy)"  required  value="${not empty sua ? sua.getNgayXN() : ''}"/></br>
	                <input type="text" name="txtloai" required placeholder="Loại xét nghiệm" value="${not empty sua ? sua.getLoaiXN() : ''}" > </br>
	                <input type="text" name="txtkq" required placeholder="Kết quả" value="${not empty sua ? sua.getKetQua() : ''}" > </br>
	                <input name="txtxn" type="hidden" value="${not empty sua ? sua.getMaXetNghiem() : ''}" >
	                <button type="submit" name="nutadd" class="btn-add">Thêm</button>
	                <button type="submit" name="nutupdate" class="btn-update">Lưu</button>
	            </form>
            <h1 class="content-title-list">DANH SÁCH XÉT NGHIỆM</h1>
            <table class="table table-bordered ">
                <thead>
                    <tr>
                    	<th scope="col">STT</th>
                    	<th scope="col">Mã BHYT</th>
                        <th scope="col" style="width: 120px;text-align: center;">Ngày thực hiện</th>
                        <th scope="col" style="padding-left: 10px">Loại xét nghiệm</th>
                        <th scope="col">Kết quả</th>
                        <th scope="col">Chức năng</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="xn" items="${dsxn}" varStatus="loop">
					<tr>
					<td>${loop.index + 1}</td>
					   <td>${xn.getMaBn()}</td>
					   <td style="text-align: center;">${xn.getNgayXN()}</td>
					   <td style="padding-left: 10px">${xn.getLoaiXN()}</td>
					   <td>${xn.getKetQua()}</td>
					   <td>
                            <a href="xetNghiemController?xnup=${xn.getMaXetNghiem()}">
                            <i style="margin-left: 10px;
                                font-size: 20px;
                                margin-right: 10px;" class="ri-edit-fill"></i>
                            </a>
                            <a href="xetNghiemController?xndel=${xn.getMaXetNghiem()}">
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
                        <th scope="col" style="width: 120px;text-align: center;">Ngày thực hiện</th>
                        <th scope="col" style="padding-left: 10px">Loại xét nghiệm</th>
                        <th scope="col">Kết quả</th>
                        
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="xn" items="${dsxn}" varStatus="loop">
                 <c:if test="${xn.getMaBn() eq user.getMaBN()}">
					<tr>
					   <td style="text-align: center;">${xn.getNgayXN()}</td>
					   <td style="padding-left: 10px">${xn.getLoaiXN()}</td>
					   <td>${xn.getKetQua()}</td>
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