package action.CommentsAction;

import Model.Comments;
import Service.CommentsService.CommentsServiceImpl.selectCommentsImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/17 17:29
 */
@Controller
public class SelectComments extends ActionSupport {

    private int news_id;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    private int video_id;

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    selectCommentsImpl commentsImpl;

    public selectCommentsImpl getCommentsImpl() {
        return commentsImpl;
    }

    public void setCommentsImpl(selectCommentsImpl commentsImpl) {
        this.commentsImpl = commentsImpl;
    }

    @Override
    public String execute() throws Exception {

        System.out.println(news_id);
        System.out.println(video_id);
        Comments comments;
        if (news_id != 0) {
            comments = new Comments(news_id);
        }
        else {
            comments = new Comments(0,null,null,null,0,video_id);
        }
        List<Comments> commentsList = commentsImpl.selectComments(comments);

        json = JSONArray.fromObject(commentsList);
        System.out.println(json);

        return SUCCESS;
    }
}
