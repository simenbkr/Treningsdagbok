package data.models;

public class Utholdenhet {
    private int id;
    private float lengde;
    private String enhet;

    public Utholdenhet(int id, float lengde, String enhet) {
        this.id = id;
        this.lengde = lengde;
        this.enhet = enhet;
    }

    public int getId() {
        return id;
    }

    public float getLengde() {
        return lengde;
    }

    public void setLengde(float lengde) {
        this.lengde = lengde;
    }

    public String getEnhet() {
        return enhet;
    }

    public void setEnhet(String enhet) {
        this.enhet = enhet;
    }

    @Override
    public String toString() {
        return String.format(" %4.2f %5s", lengde, enhet);
    }
}
