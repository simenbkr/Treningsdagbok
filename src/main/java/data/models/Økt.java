package data.models;

import data.dao.ØktDAO;
import data.db.DB;

import java.sql.Timestamp;

public class Økt {

    private int id;
    private int varighet;
    private Timestamp tidspunkt;
    private String form;
    private String prestasjon;
    private String notat;

    public Økt(Timestamp tidspunkt, String form) {
        this.tidspunkt = tidspunkt;
        this.form = form;
    }

    public Økt(int id, Timestamp tidspunkt, String form) {
        this(tidspunkt, form);
        this.id = id;
    }

    public Økt(int id, Timestamp tidspunkt, String form, String notat) {
        this(id, tidspunkt, form);
        this.notat = notat;
    }

    public static Økt createAndPersist(Timestamp tidspunkt, String form, String notat) {
        new ØktDAO().create(new Økt(tidspunkt, form));
        return new ØktDAO().getByID(DB.getLastInsertID("Økt"));
    }

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