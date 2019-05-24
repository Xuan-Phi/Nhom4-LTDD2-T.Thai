package tdc.edu.vn.nhom4;

public class PhepTinh {
    int img;
    String soA, pt, soB, kq;

    public PhepTinh() {
    }

    public PhepTinh(int img, String soA, String pt, String soB, String kq) {
        this.img = img;
        this.soA = soA;
        this.pt = pt;
        this.soB = soB;
        this.kq = kq;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getSoA() {
        return soA;
    }

    public void setSoA(String soA) {
        this.soA = soA;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getSoB() {
        return soB;
    }

    public void setSoB(String soB) {
        this.soB = soB;
    }

    public String getKq() {
        return kq;
    }

    public void setKq(String kq) {
        this.kq = kq;
    }
}
