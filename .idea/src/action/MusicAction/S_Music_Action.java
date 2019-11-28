package action.MusicAction;

import Model.Music;
import Service.MusicService.MusicServiceImpl.SelectMusicImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/19 11:40
 */
@Controller
public class S_Music_Action extends ActionSupport {

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
    SelectMusicImpl selectMusicImpl;

    public SelectMusicImpl getSelectMusicImpl() {
        return selectMusicImpl;
    }

    public void setSelectMusicImpl(SelectMusicImpl selectMusicImpl) {
        this.selectMusicImpl = selectMusicImpl;
    }

    @Override
    public String execute() throws Exception {

        List<Music> music = selectMusicImpl.se_mu();

        json = JSONArray.fromObject(music);
        System.out.println(json);

        return SUCCESS;
    }
}
