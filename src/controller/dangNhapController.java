package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.benhNhanbean;

import bo.benhNhanbo;
import nl.captcha.Captcha;
import security.ecrypt;

/**
 * Servlet implementation class dangNhapController
 */
@WebServlet("/dangNhapController")
public class dangNhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangNhapController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			benhNhanbo bnbo = new benhNhanbo();
			
			HttpSession session = request.getSession();
			Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
			ecrypt hashEcrypt = new ecrypt();
			request.setCharacterEncoding("UTF-8");
			String answer = request.getParameter("answer");
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			
			
			if(user != null && pass != null) {
				String hashedPassword = hashEcrypt.encrypt(pass);
				if(bnbo.dangNhapAdmin(user, hashedPassword) && captcha.isCorrect(answer)) {
					session.setAttribute("admin", true);
					response.sendRedirect("trangChuController");
				} else {
					benhNhanbean bn = bnbo.dangNhap(user, hashedPassword);
					if(bn!= null && captcha.isCorrect(answer)) {
						session.setAttribute("user", bn);
						
						response.sendRedirect("trangChuController");
						return ;
					}
					else {
						//System.out.println("Sai");
						request.setAttribute("check", "login-falied");
					}
					request.setAttribute("check", "login-falied");
				}
			}	
			RequestDispatcher rd = request.getRequestDispatcher("dangnhap.jsp");
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
