package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.bacSibean;
import bean.benhNhanbean;
import bo.bacSibo;
import bo.benhNhanbo;

/**
 * Servlet implementation class bacSiController
 */
@WebServlet("/bacSiController")
public class bacSiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bacSiController() {
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
        	DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            bacSibo bsbo = new bacSibo();
            String xoaBS = request.getParameter("mbsxoa");
            String suaBs = request.getParameter("mbsup");
            //ArrayList<bacSibean> ds = bsbo.getBS();
            if(xoaBS!= null) {
            	bsbo.XoaBS(xoaBS);
            	ArrayList<bacSibean> ds = bsbo.getBS();
	            request.setAttribute("dsbs", ds);
//	            System.out.println("Thành công ");
            } else if (suaBs != null) {
            	request.setAttribute("upbs", bsbo.getInfoBS(suaBs));
            }
            
        	if(request.getContentLength()<=0) {//Chay lan dau
        		ArrayList<bacSibean> ds = bsbo.getBS();
	            request.setAttribute("dsbs", ds);
				RequestDispatcher rd= request.getRequestDispatcher("bacSi.jsp");
				rd.forward(request, response);
				return;
			} 
            List<FileItem> fileItems = upload.parseRequest(request);
            // Create variables to store form data
            String maBacSi = "";
            String tenBacSi = "";
            String nganhBacSi = "";
            String anhBacSi = "";

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    // File upload
                    String nameimg = fileItem.getName();
                    if (!nameimg.equals("")) {
                        String dirUrl = request.getServletContext().getRealPath("") + File.separator + "image_doctor";
                        File dir = new File(dirUrl);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String fileImg = dirUrl + File.separator + nameimg;
                        File file = new File(fileImg);
                        try {
                            fileItem.write(file);
                            anhBacSi = "image_doctor" + "/" + nameimg;  // Save the file name to the database
                            System.out.println("UPLOAD THÀNH CÔNG...!");
                            System.out.println("Đường dẫn lưu file là: " + dirUrl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // Form fields
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8");
     
                    switch (fieldName) {
                    case "txtmabs":
                        maBacSi = fieldValue;
                        break;
                    case "txttenbs":
                        tenBacSi = URLEncoder.encode(fieldValue, "UTF-8");;
                        break;
                    case "txtnganh":
                    	nganhBacSi = URLEncoder.encode(fieldValue, "UTF-8");;
                        break;
                    case "txtanh":
                        anhBacSi = "image_doctor" + "/" + fieldValue;
                        break;
                    case "nutadd":
                    	if(bsbo.checkMaBS(maBacSi)) {            		
                    		request.setAttribute("check", "falied");
                    		// Get the updated list of doctors
                    		ArrayList<bacSibean> updatedDs = bsbo.getBS();
                    		// Set the updated list as an attribute in the request
                    		request.setAttribute("dsbs", updatedDs);
                    	}
                    	else {
                    		bsbo.addBacSi(maBacSi, tenBacSi, nganhBacSi, anhBacSi);
                    		// Get the updated list of doctors
                    		ArrayList<bacSibean> updatedDs = bsbo.getBS();
                    		// Set the updated list as an attribute in the request
                    		request.setAttribute("dsbs", updatedDs);
    					}
                    	break;
                    case "nutupdate":
                    	bsbo.SuaBS(maBacSi, tenBacSi, nganhBacSi, anhBacSi);
                    	// Get the updated list of doctors
                		ArrayList<bacSibean> updatedDs = bsbo.getBS();
                		// Set the updated list as an attribute in the request
                		request.setAttribute("dsbs", updatedDs);
                    	break;
                }
                    
                } 
            }
            
            // Redirect or forward to a success page
            RequestDispatcher rd = request.getRequestDispatcher("bacSi.jsp");
            rd.forward(request, response);
    
        } catch (Exception e) {
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
