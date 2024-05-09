package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.benhNhanbo;
import security.ecrypt;

/**
 * Servlet implementation class dangKyController
 */
@WebServlet("/dangKyController")
public class dangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangKyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");//gửi lên sever bằng mã utf-8
			response.setCharacterEncoding("UTF-8");//trả về client bằng mã utf-8
			String mabn = request.getParameter("mabn");
			String hodem = request.getParameter("hodem");
			String tenbn = request.getParameter("tenbn");
			String pass = request.getParameter("password");
			String ngaysinh = request.getParameter("ngaysinh");
			String gt = request.getParameter("gioitinh");
			String diachi = request.getParameter("diachi");
			String sodt = request.getParameter("sodt");
			String btn= request.getParameter("dangky");
			ecrypt hashEcrypt = new ecrypt();
			if(btn!=null) {
				if(mabn!= null && hodem!= null && tenbn!=null && pass!=null && ngaysinh!=null 
						&& gt!=null && diachi!=null && sodt!=null ) {
					benhNhanbo bnbo = new benhNhanbo();
					if(!bnbo.checkUsername(mabn)) {
						// Định dạng cho ngày tháng trong chuỗi đầu vào
					    SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

					    // Chuyển đổi chuỗi ngày tháng sang kiểu Date
					    Date dateNgaySinh = inputDateFormat.parse(ngaysinh);

					    // Định dạng lại Date thành chuỗi theo định dạng mới
					    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    String ngaySinhFormatted = outputDateFormat.format(dateNgaySinh);
					    
					    String hashedPassword = hashEcrypt.encrypt(pass);
					    
						bnbo.dangky(mabn, hodem, tenbn, ngaySinhFormatted, gt, diachi, sodt, hashedPassword);
						response.sendRedirect("dangNhapController");
			            return;
					}
					else {
						request.setAttribute("check", "falied");
					}
				} 
			}
				
			RequestDispatcher rd = request.getRequestDispatcher("dangky.jsp");
	        rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
