package data.models;

public class Styrke {

    private int id;
    private float belastning;
    private int sett;
    private int reps;

    public Styrke(int id, float belastning, int reps, int sett) {
        this.id = id;
        this.belastning = belastning;
        this.reps = reps;
        this.sett = sett;
    }

    public int getId() {
        return id;
    }

    public float getBelastning() {
        return belastning;
    }

    public void setBelastning(float belastning) {
        this.belastning = belastning;
    }

    public int getSett() {
        return sett;
    }

    public void setSett(int sett) {
        this.sett = sett;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return String.format(" %3.2f kg | ", belastning) + String.format("%3d rep | ", reps) + String.format("%2d sett", sett);
    }
}
