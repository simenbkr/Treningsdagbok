package data.models;

import java.sql.Timestamp;

public class Økt {

    private int id;
    private int varighet;
    private Timestamp tidspunkt;
    private String form;
    private String prestasjon;
    private String notat;

    public int getId(){
        return this.id;
    }

    public int getVarighet(){
        return this.varighet;
    }

    public void setVarighet(int varighet){
        this.varighet = varighet;
    }

    public Timestamp getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(Timestamp tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getPrestasjon() {
        return prestasjon;
    }

    public void setPrestasjon(String prestasjon) {
        this.prestasjon = prestasjon;
    }

    public String getNotat() {
        return notat;
    }

    public void setNotat(String notat) {
        this.notat = notat;
    }
}