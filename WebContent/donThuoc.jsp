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
    <link rel="stylesheet" href="stylehome.css">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Đơn thuốc</title>
</head>

<body>
    <section class="header">
        <a href="trangChuController" class="logo">
            <i class="ri-menu-line icon icon-0 menu"></i>
            <h2>MedEx</h2>
        </a>
        <div class="search--notification--profile">
            <form class="search" action = "benhNhanController" method = "post" >
		      <input name="txttk" placeholder="Search...">
		      <button type="submit" name="nuttk"><i class="ri-search-2-line"></i></button>
	    	</form>
            <div class="notification--profile">
                <div class="picon lock">
                    <i class="ri-lock-line"></i>
                </div>
                <div class="picon bell">
                    <i class="ri-notification-2-line"></i>
                </div>
                <div class="picon chat">
                    <i class="ri-wechat-2-line"></i>
                </div>
            </div>
        </div>
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
        <h1 class="content-title-info">THÔNG TIN ĐƠN THUỐC</h1>
        <c:choose>
            	<c:when test="${not empty admin}">
            		<form id="patientForm" action="donThuocController" method="POST">
	             <select id="gender" name="txtbs" class="type_gender" style="width: 350px;">
					    <c:forEach var="bs" items="${dsbs}">
					        <option value="${bs.getMaBacSi()}" ${not empty dtsua && dtsua.getMaBS() eq bs.getMaBacSi() ? 'selected' : ''}>
					            ${bs.getMaBacSi()}
					            <span>(${URLDecoder.decode(bs.getTenBacSi(), 'UTF-8')})</span>
					        </option>
					    </c:forEach>
					</select></br>
	                <select id="gender" name="txtbn" class="type_gender" style="width: 350px;">
					    <c:forEach var="bn" items="${dsbn}">
					        <option value="${bn.getMaBN()}" ${not empty dtsua && dtsua.getMaBn() eq bn.getMaBN() ? 'selected' : ''}>
					            ${bn.getMaBN()}
					            <span>(${bn.getHoDem()} ${bn.getTenBN()})</span>
					        </option>
					    </c:forEach>
					</select></br>

					<input type="date" name="ngaykd" placeholder="Ngày kê đơn (dd/MM/yyyy)"  required value="${not empty dtsua ? dtsua.getNgayKeDon() : ''}" /></br>
	                <input type="text" name="txttt" required placeholder="Tên thuốc" value="${not empty dtsua ? dtsua.getTenThuoc() : ''}" > </br>
	                <input type="text" name="txtld" required placeholder="Liều dùng" value="${not empty dtsua ? dtsua.getLieuDung() : ''}"  > </br>
	                <input type="text" name="txtsd" required placeholder="Cách sử dụng" value="${not empty dtsua ? dtsua.getCachSD() : ''}" > </br>
	                <input name="txtdt" type="hidden" value="${not empty dtsua ? dtsua.getMaDT() : ''}">
	                <button type="submit" name="nutadd" class="btn-add">Thêm</button>
	                <button type="submit" name="nutupdate" class="btn-update">Lưu</button>
	            </form>
            <h1 class="content-title-list">DANH SÁCH ĐƠN THUỐC</h1>
            <table class="table table-bordered ">
                <thead>
                    <tr>
                    	<th scope="col" >STT</th>
                    	<th scope="col" style="text-align: center;" >Mã BHYT</th>
                        <th scope="col" style="width: 116px;text-align: center;">Ngày kê đơn</th>
                        <th scope="col" style="text-align: center;">Tên bác sĩ</th>
                        <th scope="col">Tên thuốc</th>
                        <th scope="col">Liều dùng</th>
                        <th scope="col">Cách sử dụng</th>
                        <th scope="col" style="width: 84px;">Chức năng</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="dt" items="${dsdt}" varStatus="loop">
					<tr>
						<th scope="row">${loop.index + 1}</th>
					   <td style="text-align: center;">${dt.getMaBn()}</td>
					   <td style="text-align: center;">${dt.getNgayKeDon()}</td>
					   <td style="text-align: center;" >${URLDecoder.decode(dt.getMaBS(), 'UTF-8')}</td>
					   <td >${dt.getTenThuoc()}</td>
					   <td >${dt.getLieuDung()}</td>
					   <td>${dt.getCachSD()}</td>
					   <td>
                            <a href="donThuocController?dtup=${dt.getMaDT()}">
                            <i style="margin-left: 10px;
                                font-size: 20px;
                                margin-right: 10px;" class="ri-edit-fill"></i>
                            </a>
                            <a href="donThuocController?dtdel=${dt.getMaDT()}">
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
				 <table class="table table-bordered">
				    <thead>
				        <tr>
				            <th scope="col" style="width: 116px;text-align: center;">Ngày kê đơn</th>
				            <th scope="col" style="padding-left: 10px">Mã bác sĩ</th>
				            <th scope="col">Tên thuốc</th>
				            <th scope="col">Liều dùng</th>
				            <th scope="col">Cách sử dụng</th>
				        
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach var="dt" items="${dsdt}" varStatus="loop">
				            <c:if test="${dt.getMaBn() eq user.getMaBN()}">
				                <tr>
				                    <td style="text-align: center;">${dt.getNgayKeDon()}</td>
				                    <td style="padding-left: 14px" >${dt.getMaBS()}</td>
				                    <td >${dt.getTenThuoc()}</td>
				                    <td>${dt.getLieuDung()}</td>
				                    <td>${dt.getCachSD()}</td>
				              
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