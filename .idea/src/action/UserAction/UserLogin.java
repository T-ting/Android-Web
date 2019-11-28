package action.UserAction;

import Model.Admin;
import Model.User;
import Service.UserService.UserServiceImpl.UserLoginImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/12 22:38
 */
//用户登录
@Controller
public class UserLogin extends ActionSupport {

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    String test1;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    String test;

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
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

        System.out.println("安卓端11："+test+"\n"+"222:"+test1);

        User user = new User(Integer.parseInt(test),test1);
        user = userLoginImpl.userLogin(user);

        System.out.println(user);

        json = JSONArray.fromObject(user);
        System.out.println(json);

        return SUCCESS;
    }

}
