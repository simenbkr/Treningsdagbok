package data.models;

public class Miljø {

    private int id;
    private Inne inne;
    private Ute ute;

    public Miljø(int id, Inne inne) {
        this.id = id;
        this.inne = inne;
    }

    public Miljø(int id, Ute ute) {
        this.id = id;
        this.ute = ute;
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
