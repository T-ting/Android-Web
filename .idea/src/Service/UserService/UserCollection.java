package Service.UserService;

import Model.SC;
import Model.User;

/**
 * @author 谢朝康
 * @date 2019/11/14 10:58
 */
public interface UserCollection {
    int UserCollection(User user);
    int in_collection(SC SC);
}
