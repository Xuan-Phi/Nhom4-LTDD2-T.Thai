package com.example.quanlysinhvien;

public class SinhVien {
    private String hoten;
    private boolean gioitinh; //true la nam, false la nu
    private int namsinh;
    private String quequan;

    public SinhVien() {
    }

    public SinhVien(String hoten, boolean gioitinh, int namsinh, String quequan) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.namsinh = namsinh;
        this.quequan = quequan;
    }

    @Override
    public String toString() {
        return "Họ & Tên: " + hoten + "\nNăm sinh: " + String.valueOf(namsinh) + "\nQuê quán: " + quequan;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }
}
