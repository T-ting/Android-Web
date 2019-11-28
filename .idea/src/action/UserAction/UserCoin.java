package action.UserAction;

import Model.User;
import Service.UserService.UserServiceImpl.SelectByIdImpl;
import Service.UserService.UserServiceImpl.UserCoinImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/14 10:24
 */
@Controller
public class UserCoin extends ActionSupport {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    SelectByIdImpl selectByIdImpl;

    public SelectByIdImpl getSelectByIdImpl() {
        return selectByIdImpl;
    }

    public void setSelectByIdImpl(SelectByIdImpl selectByIdImpl) {
        this.selectByIdImpl = selectByIdImpl;
    }

    @Autowired
    UserCoinImpl userCoinImpl;

    public UserCoinImpl getUserCoinImpl() {
        return userCoinImpl;
    }

    public void setUserCoinImpl(UserCoinImpl userCoinImpl) {
        this.userCoinImpl = userCoinImpl;
    }

    //用户投币
    public String push(){

        List<User> userList = new ArrayList<>();

       // int id = 1;
        User user = selectByIdImpl.SelectUserById(id);
        if (user.getMoney() == 0){
            user = null;
        }
        else {
            int money = user.getMoney() -1 ;
            user = new User(id,money);
            userCoinImpl.UserCoin(user);
        }
        userList.add(user);
        json = JSONArray.fromObject(userList);
        System.out.println(json);

        return SUCCESS;
    }

    //用户增加硬币数目
    public String add(){

        //int id = 1;
        User user = selectByIdImpl.SelectUserById(id);
        int money = user.getMoney() +1;
        user = new User(id,money);
        userCoinImpl.UserCoin(user);

        return SUCCESS;
    }

}
