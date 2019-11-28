package Service.UserService;

import Mapper.UserMapper.UserMapper;
import Model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 谢朝康
 * @date 2019/11/12 22:56
 */
public interface UserLogin {
    User userLogin(User user);
}
