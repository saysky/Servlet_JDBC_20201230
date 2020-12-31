
import java.io.*;
import java.sql.*;
 
import javax.servlet.*;
import javax.servlet.http.*;

 
public class delete extends HttpServlet {
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
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		try {
			dbDAO db = new dbDAO();
			ResultSet rs = db
					.query("select password from User where userId=?",
							userId);
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					db.modify("delete from User where password=? and userId=?",password,userId);
					errmsg = "已注销！";
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
				} else {
					errmsg = "密码错误！";
					request.setAttribute("errmsg", errmsg);
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
				}
			} else {
				errmsg = "用户名不存在！";
				request.setAttribute("errmsg", errmsg);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
}

