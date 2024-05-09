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
import bean.donThuocbean;
import bean.xetNghiembean;
import bo.bacSibo;
import bo.benhNhanbo;
import bo.donThuocbo;
import bo.xetNghiembo;

/**
 * Servlet implementation class donThuocController
 */
@WebServlet("/donThuocController")
public class donThuocController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public donThuocController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			donThuocbo dtbo = new donThuocbo();
			benhNhanbo bnbo = new benhNhanbo();
			bacSibo bsbo = new bacSibo();
			request.setCharacterEncoding("UTF-8");//gửi lên sever bằng mã utf-8
			response.setCharacterEncoding("UTF-8");//trả về client bằng mã utf-8
			ArrayList<donThuocbean> ds = dtbo.getDT();
			
			ArrayList<benhNhanbean> bn = bnbo.getBN();
			request.setAttribute("dsbn", bn);
			ArrayList<bacSibean> bs = bsbo.getBS();
			request.setAttribute("dsbs", bs);
			String maBn = request.getParameter("txtbn");
			String maBs = request.getParameter("txtbs");
			String ngayKD = request.getParameter("ngaykd");
			String tenThuoc = request.getParameter("txttt");
			String lieuDung = request.getParameter("txtld");
			String cachSD = request.getParameter("txtsd");
			String ma = request.getParameter("txtdt");
			String add = request.getParameter("nutadd");
			String xoa = request.getParameter("dtdel");
			String sua = request.getParameter("dtup");
			String luu = request.getParameter("nutupdate");
			if( add!= null) {
				dtbo.addDonThuoc(maBn, ngayKD, maBs, tenThuoc, lieuDung, cachSD);
				ds = dtbo.getDT();
			} else if (xoa != null) {
				dtbo.XoaDonThuoc(xoa);
				ds = dtbo.getDT();
			} else if (sua != null) {
				request.setAttribute("dtsua", dtbo.getInfoDT(sua));
			} else if (luu!= null) {
				dtbo.SuaDT(maBn, ngayKD, maBs, tenThuoc, lieuDung, cachSD, ma);
				ds = dtbo.getDT();
			}
			request.setAttribute("dsdt", ds);
			RequestDispatcher rd = request.getRequestDispatcher("donThuoc.jsp");
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
