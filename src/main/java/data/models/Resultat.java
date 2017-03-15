package data.models;

import data.dao.ResultatDAO;

public class Resultat {

    private int id;
    private Styrke styrke;
    private Utholdenhet utholdenhet;

    public Resultat(int id, Styrke styrke) {
        this.id = id;
        this.styrke = styrke;
    }

    public Resultat(int id, Utholdenhet utholdenhet) {
        this.id = id;
        this.utholdenhet = utholdenhet;
    }

    public Resultat(Styrke styrke) {
        this.styrke = styrke;
    }

    public Resultat(Utholdenhet utholdenhet) {
        this.utholdenhet = utholdenhet;
    }

    public static Resultat createAndPersist(Styrke styrke) {
        return new ResultatDAO().getByID(new ResultatDAO().create(new Resultat(styrke)));
    }

    public static Resultat createAndPersist(Utholdenhet utholdenhet) {
        return new ResultatDAO().getByID(new ResultatDAO().create(new Resultat(utholdenhet)));
    }

    public int getId() {
        return id;
    }

    public Styrke getStyrke() {
        return styrke;
    }

    public Utholdenhet getUtholdenhet() {
        return utholdenhet;
    }
}
