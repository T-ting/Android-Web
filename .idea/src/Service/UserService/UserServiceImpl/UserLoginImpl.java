package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.User;
import Service.UserService.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/12 22:59
 */
@Service
public class UserLoginImpl implements UserLogin {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User userLogin(User user) {
        return userMapper.userlogin(user);
    }
}
