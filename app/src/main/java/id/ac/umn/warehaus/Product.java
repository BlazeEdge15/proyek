package id.ac.umn.warehaus;

public class Product{
    private String Cost;
    private String QTY;
    private String Item, Desc, Tanggal;

    public Product() {
    }

    public Product(String cost, String desc, String item, String QTY, String tanggal) {
        this.Cost = cost;
        this.QTY = QTY;
        this.Item = item;
        this.Desc = desc;
        this.Tanggal = tanggal;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        this.Cost = cost;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }
}

