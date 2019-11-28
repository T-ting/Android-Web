package Service.MusicService.MusicServiceImpl;

import Mapper.MusicMapper.MusicMapper;
import Model.Music;
import Service.MusicService.SelectMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/19 11:39
 */
@Service
public class SelectMusicImpl implements SelectMusic {

    @Autowired
    MusicMapper musicMapper;

    public MusicMapper getMusicMapper() {
        return musicMapper;
    }

    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    @Override
    public List<Music> se_mu() {
        return musicMapper.se_mu();
    }
}
