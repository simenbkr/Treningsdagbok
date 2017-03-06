package data.models;

public class Ute extends Miljø {

    private String værforhold;
    private String værtype;
    private float temperatur;

    public Ute(int id, String værforhold, String værtype, float temperatur) {
        super(id);
        this.værforhold = værforhold;
        this.værtype = værtype;
        this.temperatur = temperatur;
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
