package Service.VideoService.VideoServiceImpl;

import Mapper.VideoMapper.VideoMapper;
import Model.Video;
import Service.VideoService.S_videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/20 20:39
 */
@Service
public class S_videoImpl implements S_videoService {

    @Autowired
    VideoMapper videoMapper;

    public VideoMapper getVideoMapper() {
        return videoMapper;
    }

    public void setVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> S_video() {
        return videoMapper.S_video();
    }
}
