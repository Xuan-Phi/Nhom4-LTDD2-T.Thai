package tdc.edu.myapplication;

public class ThucDon {
    int icon;
    String txtAn, txtUong, txtbua;

    public ThucDon(int icon, String txtAn, String txtUong, String txtbua) {
        this.icon = icon;
        this.txtAn = txtAn;
        this.txtUong = txtUong;
        this.txtbua = txtbua;
    }

    public ThucDon() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTxtAn() {
        return txtAn;
    }

    public void setTxtAn(String txtAn) {
        this.txtAn = txtAn;
    }

    public String getTxtUong() {
        return txtUong;
    }

    public void setTxtUong(String txtUong) {
        this.txtUong = txtUong;
    }

    public String getTxtbua() {
        return txtbua;
    }

    public void setTxtbua(String txtbua) {
        this.txtbua = txtbua;
    }
}
