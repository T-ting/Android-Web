package Model;

/**
 * @author 谢朝康
 * @date 2019/11/20 19:38
 */
public class Video {
    private int id;
    private String name;
    private String path;
    private String info;

    public Video() {
    }

    public Video(String name) {
        this.name = name;
    }

    public Video(int id, String name, String path, String info) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.info = info;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
