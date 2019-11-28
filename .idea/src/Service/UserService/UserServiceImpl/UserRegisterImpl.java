package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.User;
import Service.UserService.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/13 10:41
 */
@Service
public class UserRegisterImpl implements UserRegister {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int UserRegister(User user) {
        return userMapper.UserRegister(user);
    }
}
