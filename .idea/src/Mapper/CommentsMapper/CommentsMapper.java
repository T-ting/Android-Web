package Mapper.CommentsMapper;

import Model.Comments;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/14 11:16
 */
@Repository
public interface CommentsMapper {
    int writeComments(Comments comments);
    List<Comments> selectComments(Comments comments);
}
