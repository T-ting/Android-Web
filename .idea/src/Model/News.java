package Model;

/**
 * @author 谢朝康
 * @date 2019/11/13 21:07
 */
public class News {
    private int id;
    private String name;
    private String title;
    private String text;
    private String time;

    public News(int id) {
        this.id = id;
    }


    public News(String name, String title, String text, String time) {
        this.name = name;
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public News(int id, String name, String text, String time, String title) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.time = time;
        this.title = title;
    }

    public News() {
    }

    public News(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
