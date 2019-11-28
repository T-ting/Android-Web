package Model;

/**
 * @author 谢朝康
 * @date 2019/11/14 11:18
 */
//评论
public class Comments {
    private int id;
    private String name;
    private String text;
    private String time;
    private int news_id;
    private int video_id;
    public Comments() {
    }

    public Comments(String name, String text, String time) {
        this.name = name;
        this.text = text;
        this.time = time;
    }

    public Comments(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public Comments(int id, String name, String text, String time, int news_id) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.time = time;
        this.news_id = news_id;
    }

    public Comments(int id, String name, String text, String time, int news_id, int video_id) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.time = time;
        this.news_id = news_id;
        this.video_id = video_id;
    }

    public Comments(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Comments(int news_id) {
        this.news_id = news_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
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
