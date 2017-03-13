package data.models;

import data.dao.UtholdenhetDAO;
import data.db.DB;

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

    public static Utholdenhet createAndPersist(float lengde, String enhet) {
        new UtholdenhetDAO().create(new Utholdenhet(lengde, enhet));
        return new UtholdenhetDAO().getByID(DB.getLastInsertID("Utholdenhet"));
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
        if(this.enhet.equals("m") && utholdenhet.enhet.equals("km")){
            return this.compareTo(new Utholdenhet(utholdenhet.getLengde()*1000, "m"));
        } else if(utholdenhet.enhet.equals("m") && this.enhet.equals("km")){
            return this.compareTo(new Utholdenhet(utholdenhet.getLengde()/1000, "km"));
        }

        if(this.enhet.equals("s") && utholdenhet.enhet.equals("t")){
            return this.compareTo(new Utholdenhet(utholdenhet.getLengde()*60, "s"));
        } else if(utholdenhet.enhet.equals("s") && this.enhet.equals("t")){
            return this.compareTo(new Utholdenhet(utholdenhet.getLengde()/60, "t"));
        }

        if(this.enhet.equals("s") && utholdenhet.enhet.equals("m")){
            return -1;
        } else if(this.enhet.equals("t") && utholdenhet.enhet.equals("km")){
            return -1;
        } else if(this.enhet.equals("m") && utholdenhet.enhet.equals("t")){
            return -1;
        }

        if(this.lengde > utholdenhet.lengde){
            return 1;
        }
        else if(this.lengde < utholdenhet.lengde){
            return -1;
        }
        return 0;
    }
}
