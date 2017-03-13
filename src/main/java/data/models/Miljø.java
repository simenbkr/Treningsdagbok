package data.models;

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
