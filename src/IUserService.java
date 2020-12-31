import java.util.List;
  
  public interface IUserService {
      
      /** 显示所有用户 
     * @throws Exception */
      public List<User> listAll() throws Exception;
      
      /** 添加一个用户 
     * @throws Exception */
      public boolean add(User user) throws Exception;
      
      /** 根据id删除一个用户 
     * @throws Exception */
      public boolean deleteById(String userId) throws Exception;
     
      /** 更新一个用户 
     * @throws Exception */
      public boolean update(User user) throws Exception;
      
      /** 根据id查找一个用户 */
      public User getById(Integer id);
      
  }
