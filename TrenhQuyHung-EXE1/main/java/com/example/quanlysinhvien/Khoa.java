package com.example.quanlysinhvien;

import java.util.ArrayList;

public class Khoa {
    private int img;
    private String tenkhoa;
    private int soluong;

    public Khoa() {
    }

    public Khoa(int img, String tenkhoa, int soluong) {
        this.img = img;
        this.tenkhoa = tenkhoa;
        this.soluong = soluong;
    }

    public int getImg()
    {
        return img;
    }

    public void setImg(int img)
    {
        this.img = img;
    }
    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
