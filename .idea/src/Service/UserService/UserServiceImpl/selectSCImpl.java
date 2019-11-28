package Service.UserService.UserServiceImpl;

import Mapper.UserMapper.UserMapper;
import Model.SC;
import Service.UserService.selectSC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/23 16:46
 */
@Service
public class selectSCImpl implements selectSC {

    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<SC> selectSC(int use_id) {
        return userMapper.selectSC(use_id);
    }
}
