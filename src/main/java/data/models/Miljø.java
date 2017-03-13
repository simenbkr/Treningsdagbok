package data.models;

import data.dao.MiljøDAO;
import data.db.DB;

public class Miljø {

    private int id;
    private Inne inne;
    private Ute ute;

    public Miljø(Ute ute) {
        this.ute = ute;
    }

    public Miljø(Inne inne) {
        this.inne = inne;
    }

    public Miljø(int id, Ute ute) {
        this(ute);
        this.id = id;
    }

    public Miljø(int id, Inne inne) {
        this(inne);
        this.id = id;
    }

    public static Miljø createAndPersist(Ute ute) {
        new MiljøDAO().create(new Miljø(ute));
        return new MiljøDAO().getByID(DB.getLastInsertID("Miljø"));
    }

    public static Miljø createAndPersist(Inne inne) {
        new MiljøDAO().create(new Miljø(inne));
        return new MiljøDAO().getByID(DB.getLastInsertID("Miljø"));
    }

    public int getId() {
        return id;
    }

    public Inne getInne() {
        return inne;
    }

    public Ute getUte() {
        return ute;
    }
}
