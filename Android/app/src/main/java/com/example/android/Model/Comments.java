package com.example.android.Model;

/**
 * @author 谢朝康
 * @date 2019/11/14 11:18
 */
//评论
public class Comments {
    private int id;
    private  int ImageId;
    private String name;
    private String text;
    private String time;
    private String news_title;

    public Comments(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Comments(String name, String text, String time) {
        this.name = name;
        this.text = text;
        this.time = time;
    }

    public Comments(int imageId, String name, String text, String time) {
        ImageId = imageId;
        this.name = name;
        this.text = text;
        this.time = time;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
