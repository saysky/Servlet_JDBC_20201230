
import java.io.*;

import java.sql.*;
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
 
public class consume extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		String errmsg = "";
		String userId = (String) request.getSession().getAttribute("userId");
		String amount = request.getParameter("amount");
 
		try {
			dbDAO db = new dbDAO();
			db.modify("update consume set service_price=service_price+? where user_id=?", amount,userId);
			errmsg = "成功！";
			request.setAttribute("errmsg", errmsg);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
}
