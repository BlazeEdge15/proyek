package id.ac.umn.warehaus;

public class History {
    private int idx;
    private String nama;
    private int jum;

    public History(int idx, String nama, int jum) {
        this.idx = idx;
        this.nama = nama;
        this.jum = jum;
    }

    public int getIdx() {
        return idx;
    }

    public String getNama() {
        return nama;
    }

    public int getJum() {
        return jum;
    }
}
