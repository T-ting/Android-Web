package Service.NewsService.NewsServiceImpl;

import Mapper.NewsMapper.NewsMapper;
import Model.News;
import Service.NewsService.SelectAllNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/13 23:17
 */
@Service
public class SelectAllNewsImpl implements SelectAllNews {

    @Autowired
    NewsMapper newsMapper;

    public NewsMapper getNewsMapper() {
        return newsMapper;
    }

    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }


    @Override
    public List<News> selectAll() {
        return newsMapper.selectAll();
    }
}
