package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.SC;
import Model.User;
import Service.UserService.UserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/14 10:59
 */
//收藏
@Service
public class UserCollectionImpl implements UserCollection {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int UserCollection(User user) {
        return userMapper.UserCollection(user);
    }

    @Override
    public int in_collection(SC SC) {
        return userMapper.in_collection(SC);
    }
}
