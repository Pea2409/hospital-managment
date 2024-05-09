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
    <title>Quản lý bệnh nhân</title>
</head>

<body>
<c:if test="${not empty check}">
	<script>
		alert("Mã BHYT đã tồn tại trong hệ thống!!!");
	</script>
</c:if>
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
                    <h2 style="margin-bottom: 10px;margin-left: 12px;">
    				Welcome ${user.getTenBN()}</h2>
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
	            <h1 class="content-title-info">THÔNG TIN BỆNH NHÂN</h1>
	            <form id="patientForm" action="benhNhanController" method="post">
	                <input type="text" name="txtmabn" required placeholder="Mã bệnh nhân" value="${not empty suabn ? suabn.getMaBN() : ''}" ${not empty suabn ? 'readonly' : ''}></br>
	
	                <input type="text" name="txthodem" required placeholder="Họ đệm" value="${not empty suabn ? suabn.getHoDem() : ''}" ></br>
	
	                <input type="text"  name="txtten" required placeholder="Tên bệnh nhân" value="${not empty suabn ? suabn.getTenBN() : ''}"></br>
	                <input type="date" name="ngaysinh" placeholder="Ngày sinh (dd/MM/yyyy)" required value="${not empty suabn ? suabn.getNgaySinh() : ''}" /></br>
	
	                <label for="gender" class="gender">Giới tính:</label>
	                <select id="gender" name="txtgioitinh" class="type_gender">
					    <option value="Nam" ${not empty suabn && suabn.getGioiTinh() == 'Nam' ? 'selected' : ''}>Nam</option>
					    <option value="Nữ" ${not empty suabn && suabn.getGioiTinh() == 'Nữ' ? 'selected' : ''}>Nữ</option>
					    <option value="Khác" ${not empty suabn && suabn.getGioiTinh() == 'Khác' ? 'selected' : ''}>Khác</option>
					</select><br/>
	                <input type="text" id="address" name="txtdiachi" required placeholder="Địa chỉ" value="${not empty suabn ? suabn.getDiaChi() : ''}"></br>
	                <input type="tel" id="phoneNumber" name="txtsodt" pattern="[0-9]{10}" required
	                    placeholder="Số điện thoại" value="${not empty suabn ? suabn.getSoDT() : ''}"> </br>
	                <input type="password" name="txtpass" required placeholder="Mật khẩu" value="${not empty suabn ? suabn.getPass() : ''}"> </br>
	                <button type="submit" name="nutadd" class="btn-add">Thêm</button>
	                <button type="submit" name="nutupdate" class="btn-update">Lưu</button>
	            </form>
	            <h1 class="content-title-list">DANH SÁCH BỆNH NHÂN</h1>
	            <table class="table">
                <thead>
                    <tr style=""> 
                    	<th scope="col">STT</th>
                        <th scope="col">Mã BHYT</th>
                        <th scope="col">Họ đệm</th>
                        <th scope="col">Tên</th>
                        <th scope="col">Năm sinh</th>
                        <th scope="col">Giới tính</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Số điện thoại</th>
                
                        <th scope="col">Chức năng</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="bn" items="${dsbn}" varStatus="loop">
					<tr>
					   <th scope="row">${loop.index + 1}</th>
					   <td >${bn.getMaBN()}</td>
					   <td>${bn.getHoDem()}</td>
					   <td>${bn.getTenBN()}</td>
					   <td>${bn.getNgaySinh()}</td>
					   <td>${bn.getGioiTinh()}</td>
					   <td>${bn.getDiaChi()}</td>
					   <td>${bn.getSoDT()}</td>
					   
					   <td>
                            <a href="benhNhanController?mabnup=${bn.getMaBN()}">
                            <i style="margin-left: 10px;
                                font-size: 20px;
                                margin-right: 10px;" class="ri-edit-fill"></i>
                            </a>
                            <a href="benhNhanController?mabndel=${bn.getMaBN()}">
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
	        	<div class="wrapper">
	                <div class="title">
	                    Thông tin bệnh nhân
	                </div>
	                <div class="form">
	                    <div class="inputfield">
	                        <label>Mã BHYT: </label>
	                        <span>${user.getMaBN()}</span>
	                        <!-- <input type="text" class="input" value="${user.getMaBN()}" readonly> -->
	                    </div>
	                    <div class="inputfield">
	                        <label>Họ Đệm: </label>
	                        <span>${user.getHoDem()}</span>
	                        <!-- <input type="text" class="input" value="${user.getHoDem()}" readonly> -->
	                    </div>
	                    <div class="inputfield">
	                        <label>Tên Bệnh Nhân: </label>
	                        <span>${user.getTenBN()}</span>
	                        <!-- <input type="text" class="input" value="${user.getTenBN()}" readonly> -->
	                    </div>
	                    <div class="inputfield">
	                        <label>Ngày Sinh</label>
	                        <span>${user.getNgaySinh()}</span>
	                         <!-- <input type="date" class="input" value="${user.getNgaySinh()}" readonly> -->
	                    </div>
	                    <div class="inputfield">
	                        <label>Giới Tính</label>
	                        <span>${user.getGioiTinh()}</span>
	                        <!-- <input type="text" class="input" value="${user.getGioiTinh()}" readonly> -->
	                    </div>
	                    <div class="inputfield">
	                        <label>Địa chỉ</label>
	                        <span>${user.getDiaChi()}</span>
	                        <!-- <input type="text" class="input" value="${user.getDiaChi()}" readonly> -->
	                    </div>
	                    <div class="inputfield">
	                        <label>Số điện thoại</label>
	                        <span>${user.getSoDT()}</span>
	                        <!-- <input type="text" class="input" value="${user.getSoDT()}" readonly> -->
	                    </div>
	                </div>
	            </div>
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