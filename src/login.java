
import java.io.*;
import java.sql.*;
 
import javax.servlet.*;
import javax.servlet.http.*;

 
public class login extends HttpServlet {
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
		//获取用户输入的东西
		String userId = request.getParameter("Id");
		String password = request.getParameter("password");
		try {
			//构造操作数据库的语句
			dbDAO db = new dbDAO();			
			ResultSet rs = db.query("select password from User where userId=?",userId);
			//根据不同的查询结果的，返回不同的结果到View层
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("Id", userId);
					request.getRequestDispatcher("/welcome.jsp").forward(request,response);
				} else {
					errmsg = "密码错误！";
					request.setAttribute("errmsg", errmsg);
					request.getRequestDispatcher("/index.jsp").forward(request,response);
				}
			} else {
				errmsg = "用户名不存在！";
				request.setAttribute("errmsg", errmsg);
				request.getRequestDispatcher("/index.jsp").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
}
