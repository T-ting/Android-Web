package action.NewsAction;

import Model.News;
import Service.NewsService.Se_NewsService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/26 19:18
 */
@Controller
public class Se_News extends ActionSupport {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    Se_NewsService newsService;

    public Se_NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(Se_NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public String execute() throws Exception {

        News news = new News(text);
        List<News> newsList = newsService.se_news(news);

        json = JSONArray.fromObject(newsList);

        return SUCCESS;
    }
}
