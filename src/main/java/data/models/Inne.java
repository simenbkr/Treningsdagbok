package data.models;

public class Inne extends Miljø {

    private String luft;
    private int tilskuere;

    public Inne(int id, String luft, int tilskuere) {
        super(id);
        this.luft = luft;
        this.tilskuere = tilskuere;
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
}
