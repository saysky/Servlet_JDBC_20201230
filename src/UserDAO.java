 import java.sql.Connection;
import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;

  public class UserDAO implements IUserDAO {

     @Override
     public List<User> listAll() throws Exception {
          List<User> users = new ArrayList<User>();
          dbDAO db = new dbDAO();
          String sql = "select * from User";
          try {
             PreparedStatement pst = (db.con).prepareStatement(sql);
              ResultSet rst = pst.executeQuery();
               while (rst.next()) {
                  User user = new User();
                  user.setUserId(rst.getString("userId"));
                  user.setPassword(rst.getString("password"));
                  user.setName(rst.getString("name"));
                  user.setPhone(rst.getString("Phone"));
                  user.setDocumentnumber(rst.getString("documentnumber"));
                  user.setFace(rst.getString("face"));
                  user.setCheckin_date(rst.getDate("checkin_date"));
                  user.setCheckout_date(rst.getDate("checkout_date"));
                  users.add(user);
              }
               rst.close();
               pst.close();
          } catch (Exception e) {
             e.printStackTrace();
          }
          return users;
      }

     @Override
     public boolean add(User user) throws Exception {
    	 dbDAO db = new dbDAO();
         String sql = "insert into User(userId,password,name,phone,documentnumber,face,Checkin_date,Checkout_date) values(?,?,?,?,?,?,?,?)";
         try {
             PreparedStatement pst = (db.con).prepareStatement(sql);
             pst.setString(1, user.getUserId());
             pst.setString(2, user.getPassword());
             pst.setString(3, user.getName());
             pst.setString(4, user.getPhone());
             pst.setString(5, user.getDocumentnumber());
             pst.setString(6, user.getFace());
             if(user.getCheckin_date() != null) {
                 pst.setDate(7, new java.sql.Date(user.getCheckin_date().getTime()));
             } else {
                 pst.setDate(7, null);
             }
             if(user.getCheckout_date() != null) {
                 pst.setDate(8, new java.sql.Date(user.getCheckout_date().getTime()));
             } else {
                 pst.setDate(8, null);
             }
             int count = pst.executeUpdate();
             pst.close();
             return count > 0 ? true : false;
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return false;
     }

     @Override
     public boolean deleteById(String id) throws Exception {
    	 dbDAO db = new dbDAO();
         String sql = "delete from User where userId = ?";
         try {
             PreparedStatement pst = (db.con).prepareStatement(sql);
             pst.setString(1, id);
             int count = pst.executeUpdate();
             pst.close();
             return count > 0 ? true : false;
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return false;
         }

     @Override
     public boolean update(User user) throws Exception {
    	 dbDAO db = new dbDAO();
         String sql = "update User set password = ?, name = ?, phone = ?, documentnumber = ?, face = ?, checkin_date = ?, checkout_date = ?  where userId = ?";
         try {
             PreparedStatement pst = (db.con).prepareStatement(sql);
             pst.setString(1, user.getPassword());
             pst.setString(2, user.getName());
             pst.setString(3, user.getPhone());
             pst.setString(4, user.getDocumentnumber());
             pst.setString(5, user.getFace());
             if(user.getCheckin_date() != null) {
                 pst.setDate(6, new java.sql.Date(user.getCheckin_date().getTime()));
             } else {
                 pst.setDate(6, null);
             }
             if(user.getCheckout_date() != null) {
                 pst.setDate(7, new java.sql.Date(user.getCheckout_date().getTime()));
             } else {
                 pst.setDate(7, null);
             }
             pst.setString(8, user.getUserId());

             int count = pst.executeUpdate();
//             pst.close();
             return count > 0 ? true : false;
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return false;
     }

     @Override
     public User getById(String userId) throws Exception {
         User user = new User();
         dbDAO db = new dbDAO();
         String sql = "select * from User where userId = ?";
         try {
             PreparedStatement pst = (db.con).prepareStatement(sql);
             pst.setString(1, userId);;
             ResultSet rst = pst.executeQuery();
             while (rst.next()) {
            	 user.setUserId(rst.getString("id"));
                 user.setPassword(rst.getString("password"));
                 user.setName(rst.getString("name"));
                 user.setPhone(rst.getString("Phone"));
                 user.setDocumentnumber(rst.getString("documentnumber"));
                 user.setFace(rst.getString("face"));
                 user.setCheckin_date(rst.getDate("checkin_date"));
                 user.setCheckout_date(rst.getDate("checkout_date"));
             }
             rst.close();
//             pst.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         return user;
     }

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
  }

