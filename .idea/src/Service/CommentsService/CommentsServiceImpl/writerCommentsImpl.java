package Service.CommentsService.CommentsServiceImpl;

import Mapper.CommentsMapper.CommentsMapper;
import Model.Comments;
import Service.CommentsService.writerComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/14 11:26
 */
@Service
public class writerCommentsImpl implements writerComments {

    @Autowired
    CommentsMapper commentsMapper;

    public CommentsMapper getCommentsMapper() {
        return commentsMapper;
    }

    public void setCommentsMapper(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public int writeComments(Comments comments) {
        return commentsMapper.writeComments(comments);
    }
}
