package action.UserAction;

import Model.User;
import Service.UserService.UserServiceImpl.UserLoginImpl;
import Service.UserService.UserServiceImpl.UserRegisterImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 谢朝康
 * @date 2019/11/13 10:43
 */
//用户注册
@Controller
public class UserRegister extends ActionSupport {

    int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    UserRegisterImpl userRegisterImpl;

    public UserRegisterImpl getUserRegisterImpl() {
        return userRegisterImpl;
    }

    public void setUserRegisterImpl(UserRegisterImpl userRegisterImpl) {
        this.userRegisterImpl = userRegisterImpl;
    }

    @Autowired
    UserLoginImpl userLoginImpl;

    public UserLoginImpl getUserLoginImpl() {
        return userLoginImpl;
    }

    public void setUserLoginImpl(UserLoginImpl userLoginImpl) {
        this.userLoginImpl = userLoginImpl;
    }

    @Override
    public String execute() throws Exception {

        System.out.println("测试注册");

        User user = new User(number,name,password,0,0);
        System.out.println("该账户是否已经存在："+userLoginImpl.userLogin(user));
        if ((userLoginImpl.userLogin(user)) == null) {
            int i = userRegisterImpl.UserRegister(user);
            if (i != 0) {
                user = userLoginImpl.userLogin(user);
            }
            else {
                user = null;
            }
        }
        else {
            user = null;
        }
        json = JSONArray.fromObject(user);
        System.out.println(json);

        return SUCCESS;
    }
}
