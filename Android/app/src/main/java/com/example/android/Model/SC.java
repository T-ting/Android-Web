package com.example.android.Model;

/**
 * @author 谢朝康
 * @date 2019/11/18 17:43
 */
//新闻收藏
public class SC {
    private int id;
    private int use_id;
    private int news_id;
    private int status;

    public SC() {
    }

    public SC(int status) {
        this.status = status;
    }

    public SC(int id, int use_id, int news_id) {
        this.id = id;
        this.use_id = use_id;
        this.news_id = news_id;
    }

    public SC(int id, int use_id, int news_id, int status) {
        this.id = id;
        this.use_id = use_id;
        this.news_id = news_id;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    @Override
    public String toString() {
        return "SC{" +
                "id=" + id +
                ", use_id=" + use_id +
                ", news_id=" + news_id +
                ", status=" + status +
                '}';
    }
}
