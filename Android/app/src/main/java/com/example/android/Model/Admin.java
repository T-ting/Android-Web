package com.example.android.Model;

/**
 * @author 谢朝康
 * @date 2019/10/30 21:54
 */
public class Admin {
    private String id;
    private String password;

    public Admin() {
    }

    public Admin(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
