package com.example.android.Model;

import java.io.Serializable;

/**
 * @author 谢朝康
 * @date 2019/11/12 22:50
 */
public class User implements Serializable {

    private int id;
    private String name;
    private String password;
    private int money;
    private int collection;//收藏


    public User() {
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(int id, int money) {
        this.id = id;
        this.money = money;
    }

    public User(int id, String name, String password, int money, int collection) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
        this.collection = collection;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", money='" + money + '\'' +
                ", collection='" + collection + '\'' +
                '}';
    }
}
