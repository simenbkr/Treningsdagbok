package data.models;

import java.util.Comparator;

public class Styrke implements Comparable<Styrke>{

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
