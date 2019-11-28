package com.example.android.Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/22 11:20
 */
public class VideoList implements Serializable {
    List<Video> list;

    int i;

    public List<Video> getList() {
        return list;
    }

    public void setList(List<Video> list) {
        this.list = list;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public VideoList(List<Video> list, int i) {
        this.list = list;
        this.i = i;
    }
}
