package data.models;

public class Inne extends Miljø {

  private String luft;
  private int tilskuere;
  private int inneId;

  public Inne(int id, int inneId, String luft, int tilskuere) {
    super(id);
    this.inneId = inneId;
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
