package Service.CommentsService;

import Model.Comments;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/17 17:27
 */
public interface selectComments {
    List<Comments> selectComments(Comments comments);
}
