package data.models;

import data.dao.MiljøDAO;

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
        return new MiljøDAO().getByID(new MiljøDAO().create(new Miljø(ute)));
    }

    public static Miljø createAndPersist(Inne inne) {
        return new MiljøDAO().getByID(new MiljøDAO().create(new Miljø(inne)));
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
