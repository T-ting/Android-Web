package Mapper.VideoMapper;

import Model.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/20 20:35
 */
@Repository
public interface VideoMapper {
    List<Video> S_video();
    List<Video> Sou_video(Video video);
}
