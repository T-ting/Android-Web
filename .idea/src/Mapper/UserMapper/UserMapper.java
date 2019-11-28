package Mapper.UserMapper;

import Model.SC;
import Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/12 22:47
 */
@Repository
public interface UserMapper {
    User userlogin(User user);
    int UserRegister(User user);
    User SelectUserById(int id);
    int UserCoin(User user);
    int UserCollection(User user);
    int in_collection(SC SC);
    List<SC> selectSC(int use_id);
    int de_sc(SC sc);
}
