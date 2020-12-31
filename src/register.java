
import java.io.*;

import java.sql.*;
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
 
public class register extends HttpServlet {
	// 防止用户直接输入网址访问此Servlet
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintStream out = new PrintStream(response.getOutputStream());
		response.setContentType("text/html;charSet=utf-8");
		out.print("请正常打开此页");
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		String errmsg = "";
		String userId = request.getParameter("Id");
		String password = request.getParameter("password");
		String passwordAgain = request.getParameter("passwordAgain");
		String name = request.getParameter("name");
		String documentnumber = request.getParameter("documentnumber");
	    String phone = request.getParameter("phone");
	    String face = request.getParameter("face");
	    
 
		try {
			if (password.equals(passwordAgain)) {
				dbDAO db = new dbDAO();
				ResultSet rs = db.query(
						"select name from User where userId=?",userId);
				if (!rs.next()) {
					db.insert(
							"insert into User(userId,password,name,phone,documentnumber,face) values(?,?,?,?,?,?)",
							userId,password,name,phone,documentnumber,face);
					errmsg = "注册成功！";
					request.setAttribute("errmsg", errmsg);
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
				} else {
					errmsg = "用户名已存在！";
					request.setAttribute("errmsg", errmsg);
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
				}
			} else {
				errmsg = "两次输入的密码不一致";
				request.setAttribute("errmsg", errmsg);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
}
