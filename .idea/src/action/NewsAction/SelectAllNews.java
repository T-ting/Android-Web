package action.NewsAction;

import Model.News;
import Service.NewsService.NewsServiceImpl.SelectAllNewsImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/13 23:34
 */
//查询所有新闻 一次10条
@Controller
public class SelectAllNews extends ActionSupport {

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    SelectAllNewsImpl selectAllNewsImpl;

    public SelectAllNewsImpl getSelectAllNewsImpl() {
        return selectAllNewsImpl;
    }

    public void setSelectAllNewsImpl(SelectAllNewsImpl selectAllNewsImpl) {
        this.selectAllNewsImpl = selectAllNewsImpl;
    }

    @Override
    public String execute() throws Exception {

        List<News> newsList = selectAllNewsImpl.selectAll();

        json = JSONArray.fromObject(newsList);
        System.out.println(json);

        return SUCCESS;
    }
}
