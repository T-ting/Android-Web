package Mapper.NewsMapper;

import Model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/13 21:11
 */
@Repository
public interface NewsMapper {
  //  List<News> sel(News news);//一对一关联查询
    List<News> selectAll();//查询所有新闻
    int writernews(News news);
    News selectNewsByid(News news);//根据新闻title查询新闻信息
    List<News> se_news(News news); //模糊查询
}
