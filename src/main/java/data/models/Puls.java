package data.models;

import java.sql.Timestamp;

public class Puls {

    private int id;
    private Timestamp tid;
    private int puls;
    private double lengde, bredde, høyde;
    private int øktId;

    public int getId() {
        return id;
    }

    public Timestamp getTid() {
        return tid;
    }

    public void setTid(Timestamp tid) {
        this.tid = tid;
    }

    public int getPuls() {
        return puls;
    }

    public void setPuls(int puls) {
        this.puls = puls;
    }

    public double getLengde() {
        return lengde;
    }

    public void setLengde(double lengde) {
        this.lengde = lengde;
    }

    public double getBredde() {
        return bredde;
    }

    public void setBredde(double bredde) {
        this.bredde = bredde;
    }

    public double getHøyde() {
        return høyde;
    }

    public void setHøyde(double høyde) {
        this.høyde = høyde;
    }

    public int getØktId() {
        return øktId;
    }

    public void setØktId(int øktId) {
        this.øktId = øktId;
    }
}
