package Service.NewsService.NewsServiceImpl;

import Mapper.NewsMapper.NewsMapper;
import Model.News;
import Service.NewsService.Se_NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/26 19:15
 */
@Service
public class Se_newsServiceImpl implements Se_NewsService {

    @Autowired
    NewsMapper newsMapper;

    public NewsMapper getNewsMapper() {
        return newsMapper;
    }

    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public List<News> se_news(News news) {
        return newsMapper.se_news(news);
    }
}
