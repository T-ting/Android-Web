package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.User;
import Service.UserService.UserCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/14 10:22
 */
//用户投币 或者 用户通过签到或充值增加自己的硬币数目
@Service
public class UserCoinImpl implements UserCoin {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int UserCoin(User user) {
        return userMapper.UserCoin(user);
    }
}
