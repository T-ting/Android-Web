package Service.VideoService.VideoServiceImpl;

import Mapper.VideoMapper.VideoMapper;
import Model.Video;
import Service.VideoService.Sou_videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/26 16:15
 */
@Service
public class Sou_videoServiceImpl implements Sou_videoService {

    @Autowired
    VideoMapper videoMapper;

    public VideoMapper getVideoMapper() {
        return videoMapper;
    }

    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> Sou_video(Video video) {
        return videoMapper.Sou_video(video);
    }
}
