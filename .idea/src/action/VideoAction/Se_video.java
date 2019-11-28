package action.VideoAction;

import Model.Video;
import Service.VideoService.VideoServiceImpl.S_videoImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/20 20:41
 */
@Controller
public class Se_video extends ActionSupport {

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    S_videoImpl s_video;

    public S_videoImpl getS_video() {
        return s_video;
    }

    public void setS_video(S_videoImpl s_video) {
        this.s_video = s_video;
    }

    @Override
    public String execute() throws Exception {

        List<Video> videos = s_video.S_video();

        json = JSONArray.fromObject(videos);

        return SUCCESS;
    }
}
