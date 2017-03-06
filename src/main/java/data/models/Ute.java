package data.models;

public class Ute {

    private int id;
    private String værforhold;
    private String værtype;
    private float temperatur;

    public Ute(int id, String værforhold, String værtype, float temperatur) {
        this.id = id;
        this.værforhold = værforhold;
        this.værtype = værtype;
        this.temperatur = temperatur;
    }

    public int getId() {
        return id;
    }

    public String getVærforhold() {
        return værforhold;
    }

    public void setVærforhold(String værforhold) {
        this.værforhold = værforhold;
    }

    public String getVærtype() {
        return værtype;
    }

    public void setVærtype(String værtype) {
        this.værtype = værtype;
    }

    public float getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(float temperatur) {
        this.temperatur = temperatur;
    }
}
