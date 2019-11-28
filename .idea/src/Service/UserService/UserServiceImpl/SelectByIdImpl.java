package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.User;
import Service.UserService.SelectById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/14 10:19
 */
//根据id查询用户信息
@Service
public class SelectByIdImpl implements SelectById {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User SelectUserById(int id) {
        return userMapper.SelectUserById(id);
    }
}
