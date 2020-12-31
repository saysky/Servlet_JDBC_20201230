import java.util.List;
  
public class UserService implements IUserService {
 
    public List<User> listAll() throws Exception {
         IUserDAO userDao = new UserDAO();
         return userDao.listAll();
     }
  
    public boolean add(User user) throws Exception {
    	 IUserDAO userDao = new UserDAO();
         return userDao.add(user);
     }
 
    public boolean deleteById(String id) throws Exception {
    	  IUserDAO userDao = new UserDAO();
          return userDao.deleteById(id);
     }
  
    public boolean update(User user) throws Exception {
    	  IUserDAO userDao = new UserDAO();
          return userDao.update(user);
       }
  
    public User getById(String userId) throws Exception {
    	 IUserDAO userDao = new UserDAO();
          return userDao.getById(userId);
      }

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
  
  }
 