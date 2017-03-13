package data.models;

import data.dao.StyrkeDAO;
import data.db.DB;

public class Styrke implements Comparable<Styrke>{

    private int id;
    private float belastning;
    private int sett;
    private int reps;

    public Styrke(float belastning, int reps, int sett) {
        this.belastning = belastning;
        this.reps = reps;
        this.sett = sett;
    }

    public Styrke(int id, float belastning, int reps, int sett) {
        this(belastning, reps, sett);
        this.id = id;
    }

    public static Styrke createAndPersist(float belastning, int reps, int sett) {
        new StyrkeDAO().create(new Styrke(belastning, reps, sett));
        return new StyrkeDAO().getByID(DB.getLastInsertID("Styrke"));
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

    public int compareTo(Styrke styrke){
        float res = this.getBelastning()*this.getReps()*this.getSett() - styrke.getReps()*styrke.getSett()*styrke.getBelastning();
        if(res < 0){
            return -1;
        }
        else if(res > 0){
            return 1;
        }
        return 0;
    }
}
