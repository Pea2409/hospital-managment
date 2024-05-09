package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.bacSibo;
import bo.benhNhanbo;
import bo.xetNghiembo;

/**
 * Servlet implementation class trangChuBV
 */
@WebServlet("/trangChuController")
public class trangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trangChuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			benhNhanbo bnbo = new benhNhanbo();
			bacSibo bsbo = new bacSibo();
			xetNghiembo xnbo = new xetNghiembo();
			request.setAttribute("ttbs", bsbo.getBS());
			request.setAttribute("slbs", bsbo.soLuongBacSi());
			request.setAttribute("slbn", bnbo.soLuongBenhNhan());
			request.setAttribute("slsv", bnbo.soLuongSV());
			request.setAttribute("slhs", bnbo.soLuongHS());
			request.setAttribute("slte", bnbo.soLuongTE());
			request.setAttribute("slgd", bnbo.soLuongGD());
			request.setAttribute("slxn", xnbo.soLuongXetNghiem());

			
			RequestDispatcher rd = request.getRequestDispatcher("trangChu.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
