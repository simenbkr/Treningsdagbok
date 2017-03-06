package data.models;

public class Øvelse {

    private int id;
    private String navn;
    private String beskrivelse;
    private String type;

    public Øvelse(int id, String navn, String beskrivelse, String type) {
        this.id = id;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.type = type;
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