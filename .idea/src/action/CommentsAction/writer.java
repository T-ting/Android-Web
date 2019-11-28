package action.CommentsAction;

import Mapper.UserMapper.UserMapper;
import Model.Comments;
import Model.User;
import Service.CommentsService.CommentsServiceImpl.writerCommentsImpl;
import Service.UserService.UserServiceImpl.SelectByIdImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 谢朝康
 * @date 2019/11/14 11:36
 */
//评论
@Controller
public class writer extends ActionSupport{

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int news_id;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public int video_id;

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    private String pl;

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
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
    writerCommentsImpl writerImpl;

    public writerCommentsImpl getWriterImpl() {
        return writerImpl;
    }

    public void setWriterImpl(writerCommentsImpl writerImpl) {
        this.writerImpl = writerImpl;
    }

    public String userWriter() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());

        User user = selectByIdImpl.SelectUserById(id);
        System.out.println(user);

        Comments comments = new Comments(0, user.getName(), pl, date, news_id,video_id);
        int i = writerImpl.writeComments(comments);

        json = JSONArray.fromObject(i);

        return SUCCESS;
    }
}
