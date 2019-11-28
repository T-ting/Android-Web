package Mapper.MusicMapper;

import Model.Music;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author 谢朝康
 * @date 2019/11/18 21:59
 */
@Repository
public interface MusicMapper {
    List<Music> se_mu();
    List<Music> sel_music(Music music);
}
