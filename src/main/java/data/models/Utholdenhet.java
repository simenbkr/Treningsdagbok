package data.models;

public class Utholdenhet implements Comparable<Utholdenhet>{
    private int id;
    private float lengde;
    private String enhet;

    public Utholdenhet(float lengde, String enhet) {
        this.lengde = lengde;
        this.enhet = enhet;
    }

    public Utholdenhet(int id, float lengde, String enhet) {
        this(lengde, enhet);
        this.id = id;
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

    public int compareTo(Utholdenhet utholdenhet) {
        if(this.lengde > utholdenhet.lengde){
            return 1;
        }
        else if(this.lengde < utholdenhet.lengde){
            return -1;
        }
        return 0;
    }
}
