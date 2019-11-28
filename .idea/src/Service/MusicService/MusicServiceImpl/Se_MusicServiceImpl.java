package Service.MusicService.MusicServiceImpl;

import Mapper.MusicMapper.MusicMapper;
import Model.Music;
import Service.MusicService.Se_MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/26 19:17
 */
@Service
public class Se_MusicServiceImpl implements Se_MusicService {

    @Autowired
    MusicMapper musicMapper;

    public MusicMapper getMusicMapper() {
        return musicMapper;
    }

    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    @Override
    public List<Music> Se_music(Music music) {
        return musicMapper.sel_music(music);
    }
}
