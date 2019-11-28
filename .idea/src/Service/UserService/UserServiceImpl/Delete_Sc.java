package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.SC;
import Service.UserService.De_sc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/23 19:43
 */
@Service
public class Delete_Sc implements De_sc {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int de_sc(SC sc) {
        return userMapper.de_sc(sc);
    }
}
