package controller;


import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.benhNhanbean;
import bo.benhNhanbo;
import security.ecrypt;
/**
 * Servlet implementation class benhNhanController
 */
@WebServlet("/benhNhanController")
public class benhNhanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public benhNhanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			benhNhanbo bnbo = new benhNhanbo();
			ecrypt hashEcrypt = new ecrypt();
			request.setCharacterEncoding("UTF-8");//gửi lên sever bằng mã utf-8
			response.setCharacterEncoding("UTF-8");//trả về client bằng mã utf-8
			
			ArrayList<benhNhanbean> ds = bnbo.getBN();
			String maBn = request.getParameter("txtmabn");
			String hoDem = request.getParameter("txthodem");
			String tenBn = request.getParameter("txtten");
			String ngaySinh = request.getParameter("ngaysinh");
			String gioiTinh = request.getParameter("txtgioitinh");
			String diaChi = request.getParameter("txtdiachi");
			String soDienThoai = request.getParameter("txtsodt");
			String matKhau = request.getParameter("txtpass");
			String add = request.getParameter("nutadd");
			String xoa = request.getParameter("mabndel");
			String sua = request.getParameter("mabnup");
			String luu = request.getParameter("nutupdate");
			if(add!=null) {
				if(!bnbo.checkUsername(maBn)) {
					String hashedPassword = hashEcrypt.encrypt(matKhau);
					bnbo.dangky(maBn, hoDem, tenBn, ngaySinh, gioiTinh, diaChi, soDienThoai, hashedPassword);
					ds = bnbo.getBN();
				} else {
					request.setAttribute("check", "failed");
				}
			} else if(xoa!=null) {
				bnbo.XoaBN(xoa);
				ds = bnbo.getBN();
			} else if (sua!= null) {
				request.setAttribute("suabn", bnbo.infoUser(sua));
			} else if (luu!= null) {
				String hashed = hashEcrypt.encrypt(matKhau);
				bnbo.SuaBN(maBn, hoDem, tenBn, ngaySinh, gioiTinh, diaChi, soDienThoai, hashed);
				ds = bnbo.getBN();
			}			
			if(ds!= null) {
				String tk = request.getParameter("nuttk");
				String key = request.getParameter("txttk");
				if (tk!= null) {
					if (key!= null) {
						ds=bnbo.Tim(key);
					}
						
			}
			}
			
			request.setAttribute("dsbn", ds);
			RequestDispatcher rd = request.getRequestDispatcher("benhNhan.jsp");
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
