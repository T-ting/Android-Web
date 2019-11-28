package action.UserAction;

import Model.News;
import Model.SC;
import Service.NewsService.selectNewsByTitle;
import Service.UserService.De_sc;
import Service.UserService.UserServiceImpl.selectSCImpl;
import Service.UserService.selectSC;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/23 16:48
 */
@Controller
public class SelectSCAction extends ActionSupport {

    private int user_id;

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

    private int news_id;

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    selectSC selectSCImpl;

    public selectSC getSelectSCImpl() {
        return selectSCImpl;
    }

    public void setSelectSCImpl(selectSC selectSCImpl) {
        this.selectSCImpl = selectSCImpl;
    }

    @Autowired
    De_sc de_scImpl;

    public De_sc getDe_scImpl() {
        return de_scImpl;
    }

    public void setDe_scImpl(De_sc de_scImpl) {
        this.de_scImpl = de_scImpl;
    }

    @Autowired
    selectNewsByTitle senews;

    public selectNewsByTitle getSenews() {
        return senews;
    }

    public void setSenews(selectNewsByTitle senews) {
        this.senews = senews;
    }

    @Override
    public String execute() throws Exception {

        List<News> newsList = new ArrayList<>();
        List<SC> scList = selectSCImpl.selectSC(user_id);

        for (int i = 0;i< scList.size();i++) {
           // System.out.println(scList.size()-1);
            SC sc = scList.get(i);
            System.out.println(sc);
            News news = new News(sc.getNews_id());
            news = senews.selectNewsByTitle(news);
           // System.out.println(news);
            newsList.add(news);
        }

        json = JSONArray.fromObject(newsList);
        System.out.println(json);

        return SUCCESS;
    }

    public String delectsc(){

        SC sc = new SC(user_id,news_id);
        int i = de_scImpl.de_sc(sc);

        if (i != 0){
            sc = null;
        }

        json = JSONArray.fromObject(sc);
        System.out.println(json);

        return SUCCESS;
    }
}
