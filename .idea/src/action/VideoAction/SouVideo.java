package action.VideoAction;

import Model.Video;
import Service.VideoService.Sou_videoService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/26 16:28
 */
//模糊查询
@Controller
public class SouVideo extends ActionSupport {

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
    Sou_videoService service;

    public Sou_videoService getService() {
        return service;
    }

    public void setService(Sou_videoService service) {
        this.service = service;
    }

    @Override
    public String execute() throws Exception {

        Video video = new Video(text);

        List<Video> videos = service.Sou_video(video);

        json = JSONArray.fromObject(videos);

        return SUCCESS;
    }
}
