package data.models;

public class Kategori {

    private int id;
    private String navn;
    private int foreldreId;

    public Kategori(int id, String navn) {
        this.id = id;
        this.navn = navn;
    }

    public Kategori(int id, String navn, int foreldreId) {
        this(id, navn);
        this.foreldreId = foreldreId;
    }

    public int getId(){
        return this.id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getForeldreId() {
        return foreldreId;
    }

    public void setForeldreId(int foreldreId) {
        this.foreldreId = foreldreId;
    }
}
