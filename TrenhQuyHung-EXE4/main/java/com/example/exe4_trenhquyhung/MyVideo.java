package com.example.exe4_trenhquyhung;

public class MyVideo {
    int id;
    int img;
    String name;
    String time;

    public MyVideo(int id, int img, String name, String time) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MyVideo() {
    }
}
