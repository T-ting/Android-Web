package Service.NewsService.NewsServiceImpl;

import Mapper.NewsMapper.NewsMapper;
import Model.News;
import Service.NewsService.selectNewsByTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/14 10:57
 */
//根据id查询新闻信息
@Service
public class selectNewsByTitleImpl implements selectNewsByTitle {

    @Autowired
    NewsMapper newsMapper;

    public NewsMapper getNewsMapper() {
        return newsMapper;
    }

    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public News selectNewsByTitle(News news) {
        return newsMapper.selectNewsByid(news);
    }
}
