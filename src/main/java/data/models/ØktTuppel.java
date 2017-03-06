package data.models;

public class ØktTuppel {

    private Økt økt;
    private Øvelse øvelse;
    private Miljø miljø;
    private Resultat resultat;

    public ØktTuppel(Økt økt, Øvelse øvelse, Miljø miljø, Resultat resultat) {
        this.økt = økt;
        this.øvelse = øvelse;
        this.miljø = miljø;
        this.resultat = resultat;
    }


    public Økt getØkt() {
        return økt;
    }

    public Øvelse getØvelse() {
        return øvelse;
    }

    public Miljø getMiljø() {
        return miljø;
    }

    public Resultat getResultat() {
        return resultat;
    }
}
