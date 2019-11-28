package Service.CommentsService.CommentsServiceImpl;

import Mapper.CommentsMapper.CommentsMapper;
import Model.Comments;
import Service.CommentsService.selectComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/17 17:28
 */
@Service
public class selectCommentsImpl implements selectComments {

    @Autowired
    CommentsMapper smap;

    public CommentsMapper getCommentsMapper() {
        return smap;
    }

    public void setCommentsMapper(CommentsMapper commentsMapper) {
        this.smap = commentsMapper;
    }

    @Override
    public List<Comments> selectComments(Comments comments) {
        return smap.selectComments(comments);
    }
}
