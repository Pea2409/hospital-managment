package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.bacSibean;
import bean.benhNhanbean;
import bean.nhapVienbean;
import bo.bacSibo;
import bo.benhNhanbo;
import bo.nhapVienbo;
import dao.nhapViendao;

/**
 * Servlet implementation class nhapVienController
 */
@WebServlet("/nhapVienController")
public class nhapVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nhapVienController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			nhapVienbo nvbo = new nhapVienbo();
			benhNhanbo bnbo = new benhNhanbo();
			request.setCharacterEncoding("UTF-8");//gửi lên sever bằng mã utf-8
			response.setCharacterEncoding("UTF-8");//trả về client bằng mã utf-8
			
			ArrayList<nhapVienbean> ds = nvbo.getNV();
			
			ArrayList<benhNhanbean> bn = bnbo.getBN();
			request.setAttribute("bn", bn );
			request.setAttribute("dsnv", ds);

			String maBn = request.getParameter("txtbn");
			String ngayVao = request.getParameter("ngaynv");
			String ngayRa = request.getParameter("ngayxv");
			String chuanDoan = request.getParameter("txtcd");
			String tieuSu = request.getParameter("txtts");
			String ma = request.getParameter("txtnv");
			String add = request.getParameter("nutadd");
			String xoa = request.getParameter("nvxoa");
			String sua = request.getParameter("nvup");
			String luu = request.getParameter("nutupdate");
			if( add != null) {
				nvbo.addNhapVien(maBn, ngayVao, ngayRa, chuanDoan, tieuSu);
				ds = nvbo.getNV();
			} else if (xoa != null) {
				nvbo.XoaNhapVien(xoa);
				ds = nvbo.getNV();
			} else if (sua != null) {
				request.setAttribute("nvsua", nvbo.getInfoNV(sua));
			} else if (luu != null) {
				nvbo.SuaNV(maBn, ngayVao, ngayRa, chuanDoan, tieuSu, ma);
				ds = nvbo.getNV();
			}
			 // Cập nhật danh sách
            request.setAttribute("dsnv", ds);
			RequestDispatcher rd = request.getRequestDispatcher("nhapVien.jsp");
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
