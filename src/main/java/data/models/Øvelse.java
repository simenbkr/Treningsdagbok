package data.models;

import data.dao.ØvelseDAO;

public class Øvelse {

    private int id;
    private String navn;
    private String beskrivelse;
    private String type;

    public Øvelse(String navn, String beskrivelse, String type) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.type = type;
    }

    public Øvelse(int id, String navn, String beskrivelse, String type) {
        this(navn, beskrivelse, type);
        this.id = id;
    }


    public static Øvelse createAndPersist(String navn, String beskrivelse, String type) {
        return new ØvelseDAO().getByID(new ØvelseDAO().create(new Øvelse(navn, beskrivelse, type)));
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}