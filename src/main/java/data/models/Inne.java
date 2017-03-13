package data.models;

public class Inne {

    private int id;
    private String luft;
    private int tilskuere;

    public Inne(int id, String luft, int tilskuere) {
        this.id = id;
        this.luft = luft;
        this.tilskuere = tilskuere;
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