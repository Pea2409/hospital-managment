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
import bean.xetNghiembean;
import bo.benhNhanbo;
import bo.xetNghiembo;

/**
 * Servlet implementation class xetNghiemController
 */
@WebServlet("/xetNghiemController")
public class xetNghiemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xetNghiemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			xetNghiembo xnbo = new xetNghiembo();
			benhNhanbo bnbo = new benhNhanbo();
			request.setCharacterEncoding("UTF-8");//gửi lên sever bằng mã utf-8
			response.setCharacterEncoding("UTF-8");//trả về client bằng mã utf-8
			ArrayList<benhNhanbean> bn = bnbo.getBN();
			request.setAttribute("bn", bn );
			ArrayList<xetNghiembean> ds = xnbo.getXN();
			String mabn = request.getParameter("txtbn");
			String ngayth = request.getParameter("ngayth");
			String loaixn = request.getParameter("txtloai");
			String ketqua = request.getParameter("txtkq");
			String ma = request.getParameter("txtxn");
			String add = request.getParameter("nutadd");
			String sua = request.getParameter("xnup");
			String xoa = request.getParameter("xndel");
			String luu = request.getParameter("nutupdate");
			if(add!=null) {
				xnbo.addXetNghiem(mabn, ngayth, loaixn, ketqua);
				ds = xnbo.getXN();
			}else if(xoa!=null) {
				xnbo.XoaXetNghiem(xoa);
				ds = xnbo.getXN();
			}else if(sua!=null) {
				request.setAttribute("sua", xnbo.getInfoXN(sua));
			}else if (luu!=null) {
				xnbo.SuaXN(mabn, ngayth, loaixn, ketqua, ma);
				ds = xnbo.getXN();
			}
			request.setAttribute("dsxn", ds);
			RequestDispatcher rd = request.getRequestDispatcher("xetNghiem.jsp");
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
