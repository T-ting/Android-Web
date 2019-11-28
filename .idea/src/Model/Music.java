package Model;

/**
 * @author 谢朝康
 * @date 2019/11/18 21:52
 */
//音乐
public class Music {
    private int id;
    private String name;
    private String path;//路径
    private String information;//音乐描述信息

    public Music() {
    }

    public Music(String name) {
        this.name = name;
    }

    public Music(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Music(String name, String path, String information) {
        this.name = name;
        this.path = path;
        this.information = information;
    }

    public Music(int id, String name, String path, String information) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.information = information;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
