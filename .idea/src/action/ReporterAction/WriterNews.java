package action.ReporterAction;

import Mapper.NewsMapper.NewsMapper;
import Model.News;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 谢朝康
 * @date 2019/11/13 22:09
 */
//发表新闻
@Controller
public class WriterNews extends ActionSupport {

    private String name,title,text,time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    NewsMapper newsMapper;

    public NewsMapper getNewsMapper() {
        return newsMapper;
    }

    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public String execute() throws Exception {

        News news = new News(name,title,text,time);
        System.out.println("news:"+news);

        int i = newsMapper.writernews(news);
        System.out.println("i:"+i);

        if (i != 0){
            json = JSONArray.fromObject(news);
            System.out.println(json);
        }

        return SUCCESS;
    }
}
