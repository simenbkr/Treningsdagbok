package data.models;

import data.dao.InneDAO;
import data.db.DB;

public class Inne {

    private int id;
    private String luft;
    private int tilskuere;

    public Inne(String luft, int tilskuere) {
        this.luft = luft;
        this.tilskuere = tilskuere;
    }

    public Inne(int id, String luft, int tilskuere) {
        this(luft, tilskuere);
        this.id = id;
    }

    public static Inne createAndPersist(String luft, int tilskuere) {
        new InneDAO().create(new Inne(luft, tilskuere));
        return new InneDAO().getByID(DB.getLastInsertID("Inne"));
    }

    public int getId() {
        return id;
    }

    public String getLuft() {
        return luft;
    }

    public void setLuft(String luft) {
        this.luft = luft;
    }

    public int getTilskuere() {
        return tilskuere;
    }

    public void setTilskuere(int tilskuere) {
        this.tilskuere = tilskuere;
    }

    @Override
    public String toString() {
        return String.format("%3d tilskuere | %15s", tilskuere, luft);
    }
}