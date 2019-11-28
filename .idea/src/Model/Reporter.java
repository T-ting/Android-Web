package Model;

/**
 * @author 谢朝康
 * @date 2019/11/12 23:20
 */
public class Reporter {

    private String id;
    private String name;
    private String password;
    private int coin;
    private int collection;

    public Reporter() {
    }

    public Reporter(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Reporter(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Reporter(String id, String name, String password, int coin, int collection) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.coin = coin;
        this.collection = collection;
    }

    public Reporter(String id, int coin) {
        this.id = id;
        this.coin = coin;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Reporter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
