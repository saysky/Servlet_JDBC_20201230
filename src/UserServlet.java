   import java.io.IOException;
   import java.text.ParseException;
   import java.text.SimpleDateFormat;
   import java.util.Date;
   import java.util.List;
   import javax.servlet.ServletException;
   import javax.servlet.annotation.WebServlet;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import net.sf.json.JSONArray;
   import net.sf.json.JSONObject;
   import net.sf.json.JsonConfig;

   @WebServlet("/UserServlet")
   public class UserServlet extends HttpServlet {
       
       private static final long serialVersionUID = 1L;
          
       public UserServlet() {
           super();
       }
       
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           // 设置返回编码格式，解决中文乱码
           response.setCharacterEncoding("utf-8"); 
           
           // 根据action的值来执行不同的动作，例如显示所有用户或显示单个用户
           String action = request.getParameter("action");
            
           // 显示所有用户
           if (action.equals("listAll")) {
               IUserService userService = new UserService();
               List<User> users = null;
			try {
				users = userService.listAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
               // 返回JSON数据格式
               JsonConfig jsonConfig = new JsonConfig();
               jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
               JSONArray jsonArr = JSONArray.fromObject(users, jsonConfig);
               JSONObject jsonObjOut = new JSONObject();
               jsonObjOut.put("users", jsonArr);
               JSONUtil.returnJSON(request, response, jsonObjOut);
           }
           
           // 显示单个用户
           if (action.equals("getById")) {
               int userId = Integer.parseInt(request.getParameter("userId"));
               IUserService userService = new UserService();
               User user = userService.getById(userId);
               
               // 返回JSON数据格式
               JSONObject jsonObjOut = new JSONObject();
               jsonObjOut.put("User", user);
               JSONUtil.returnJSON(request, response, jsonObjOut);
           }
       }

       // 添加用户处理

       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           // 获取JSON数据
           JSONObject jsonObjIn = JSONUtil.getJSON(request, response);
   
           User user = new User();
           user.setUserId(jsonObjIn.getString("userId"));
           user.setPassword(jsonObjIn.getString("password"));
           user.setName(jsonObjIn.getString("name"));
           user.setPhone(jsonObjIn.getString("phone"));
           user.setFace(jsonObjIn.getString("face"));
           user.setDocumentnumber(jsonObjIn.getString("documentnumber"));
           String checkin_date = jsonObjIn.getString("checkin_date");
           String checkout_date = jsonObjIn.getString("checkout_date");
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           try {
               if(checkin_date != null) {
                   user.setCheckin_date(sdf.parse(checkin_date));
               }
               if(checkout_date != null) {
                   user.setCheckout_date(sdf.parse(checkout_date));
               }
           } catch (ParseException e) {
               System.out.println("日期不能为空，或者日期格式不合法");
               e.printStackTrace();
           }

           
           IUserService userService = new UserService();
           boolean isSuccess = false;
		try {
			isSuccess = userService.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
           // 返回JSON数据格式
           JSONObject jsonObjOut = new JSONObject();
           jsonObjOut.put("isSuccess", isSuccess);
           JSONUtil.returnJSON(request, response, jsonObjOut);
       }
       
       protected void doDelete(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           // 获取JSON数据
           JSONObject jsonObjIn = JSONUtil.getJSON(request, response);
           String userId = jsonObjIn.getString("userId");
   
           IUserService userService = new UserService();
           boolean isSuccess = false;
		try {
			isSuccess = userService.deleteById(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
           // 返回JSON数据格式
           JSONObject jsonObjOut = new JSONObject();
           jsonObjOut.put("isSuccess", isSuccess);
           JSONUtil.returnJSON(request, response, jsonObjOut);
           
       }
       
       protected void doPut(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           // 获取JSON数据
          JSONObject jsonObjIn = JSONUtil.getJSON(request, response);
  
          User user = new User();
          user.setUserId(jsonObjIn.getString("userId"));
          user.setPassword(jsonObjIn.getString("password"));
          user.setName(jsonObjIn.getString("name"));
          user.setPhone(jsonObjIn.getString("phone"));
          user.setFace(jsonObjIn.getString("face"));
          user.setDocumentnumber(jsonObjIn.getString("documentnumber"));
           String checkin_date = jsonObjIn.getString("checkin_date");
           String checkout_date = jsonObjIn.getString("checkout_date");
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           try {
               if(checkin_date != null) {
                   user.setCheckin_date(sdf.parse(checkin_date));
               }
               if(checkout_date != null) {
                   user.setCheckout_date(sdf.parse(checkout_date));
               }
           } catch (ParseException e) {
               System.out.println("日期不能为空，或者日期格式不合法");
               e.printStackTrace();
           }
          IUserService userService = new UserService();
          boolean isSuccess = false;
		try {
			isSuccess = userService.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
             
          // 返回JSON数据格式
          JSONObject jsonObjOut = new JSONObject();
          jsonObjOut.put("isSuccess", isSuccess); 
          JSONUtil.returnJSON(request, response, jsonObjOut);
      }
      
  }

 