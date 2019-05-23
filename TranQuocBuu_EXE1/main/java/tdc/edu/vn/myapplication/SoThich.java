package tdc.edu.vn.myapplication;

public class SoThich {
    private int img;
    private String txt1;
    private String txt2;
    private String txt3;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getTxt3() {
        return txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }


    public SoThich(int img, String txt1, String txt2, String txt3, String txt4) {
        this.img = img;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
    }

    public SoThich() {
    }

    ;


}
