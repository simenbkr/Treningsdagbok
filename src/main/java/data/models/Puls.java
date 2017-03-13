package data.models;

import data.dao.PulsDAO;
import data.db.DB;

import java.sql.Timestamp;

public class Puls {

    private int id;
    private Timestamp tid;
    private int puls;
    private double lengde, bredde, høyde;
    private int øktId;

    public Puls(Timestamp tid, int puls, double lengde, double høyde, double bredde, int øktId) {
        this.tid = tid;
        this.puls = puls;
        this.lengde = lengde;
        this.bredde= bredde;
        this.høyde = høyde;
        this.øktId = øktId;
    }

    public Puls(int id, Timestamp tid, int puls, double lengde, double høyde, double bredde, int øktId) {
        this(tid, puls, lengde, høyde, bredde, øktId);
        this.id = id;
    }

    public static Puls createAndPersist(Timestamp tid, int puls, double lengde, double høyde, double bredde, int øktId) {
        new PulsDAO().create(new Puls(tid, puls, lengde, høyde, bredde, øktId));
        return new PulsDAO().getByID(DB.getLastInsertID("Puls"));
    }

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
