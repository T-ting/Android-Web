package action.UserAction;

import Model.SC;
import Model.User;
import Service.NewsService.NewsServiceImpl.selectNewsByTitleImpl;
import Service.UserService.UserServiceImpl.SelectByIdImpl;
import Service.UserService.UserServiceImpl.UserCollectionImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 谢朝康
 * @date 2019/11/14 11:01
 */
//收藏新闻
@Controller
public class UserCollection extends ActionSupport {

    private int user_id;

    private int news_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    selectNewsByTitleImpl selectNewsByTitleImpl;

    public Service.NewsService.NewsServiceImpl.selectNewsByTitleImpl getSelectNewsByTitleImpl() {
        return selectNewsByTitleImpl;
    }

    public void setSelectNewsByTitleImpl(Service.NewsService.NewsServiceImpl.selectNewsByTitleImpl selectNewsByTitleImpl) {
        this.selectNewsByTitleImpl = selectNewsByTitleImpl;
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
    UserCollectionImpl userCollectionImpl;

    public UserCollectionImpl getUserCollectionImpl() {
        return userCollectionImpl;
    }

    public void setUserCollectionImpl(UserCollectionImpl userCollectionImpl) {
        this.userCollectionImpl = userCollectionImpl;
    }

    @Override
    public String execute() throws Exception {

        User user = selectByIdImpl.SelectUserById(user_id);
        int money = user.getCollection() + 1;
        user = new User(user_id, money);
        int i = userCollectionImpl.UserCollection(user);
        int j = 0;
        SC sc = null;
        if (i != 0) {
            sc = new SC(0, user_id, news_id, 1);
            j = userCollectionImpl.in_collection(sc);
        }

        if (j != 0) {
            json = JSONArray.fromObject(sc);
        }

        return SUCCESS;
    }
}
