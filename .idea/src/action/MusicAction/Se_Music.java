package action.MusicAction;

import Model.Music;
import Service.MusicService.Se_MusicService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/26 19:22
 */
@Controller
public class Se_Music extends ActionSupport {

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
    Se_MusicService musicService;

    public Se_MusicService getMusicService() {
        return musicService;
    }

    public void setMusicService(Se_MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public String execute() throws Exception {

        Music music = new Music(text);
        List<Music> music1 = musicService.Se_music(music);

        json = JSONArray.fromObject(music1);

        return SUCCESS;
    }
}
