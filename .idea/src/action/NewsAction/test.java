package action.NewsAction;

import Mapper.NewsMapper.NewsMapper;
import Model.News;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/13 21:23
 */
//测试 一对一关联查询
@Controller
public class test extends ActionSupport {

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

        News news = new News("ss");
        List<News> newsList ;
        //newsList = newsMapper.sel(news);
        //System.out.println("news:"+newsList);

        json = JSONArray.fromObject(news);
        System.out.println("json:"+json);

        return SUCCESS;
    }
}
