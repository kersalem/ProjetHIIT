package com.lp.projethiit.Bd;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "seances")
public class Seance implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private int tempsEntrainement;

    private int tempsTravail;

    private int tempsReposCourt;

    private int tempsReposLong;

    private int sequence;

    private int cycle;

    // Constructeur
    // On initialise les temps
    public Seance() {
        setTempsEntrainement(6);
        setTempsTravail(2);
        setTempsReposCourt(3);
        setTempsReposLong(4);
        setSequence(8);
        setCycle(7);
    }

    public void createSeanceAvecCategories(List<Categorie> categories) {


       for (Categorie categorie : categories) {
           if (categorie.getTitle().equals("Préparation")) {
               this.tempsEntrainement = categorie.getValue();
           }
           if (categorie.getTitle().equals("Travail")) {
               this.tempsTravail = categorie.getValue();
           }
           if (categorie.getTitle().equals("Repos")) {
               this.tempsReposCourt = categorie.getValue();
           }
           if (categorie.getTitle().equals("Repos long")) {
               this.tempsReposLong = categorie.getValue();
           }
           if (categorie.getTitle().equals("Sequence")) {
               this.sequence = categorie.getValue();
           }
           if (categorie.getTitle().equals("Cycle")) {
               this.cycle = categorie.getValue();
           }
       }

    }


public ArrayList<Integer> createMaNouvelleSeance() {
ArrayList<Integer> maNouvelleSeance = new ArrayList<Integer>();

    maNouvelleSeance.add(getValueEnMiliseconds(this.tempsEntrainement));
        for(int i = 0; i < this.sequence ; i++) {
            for(int j =0; j<this.cycle;j++){
                maNouvelleSeance.add(getValueEnMiliseconds(this.tempsTravail));
                maNouvelleSeance.add(getValueEnMiliseconds(this.tempsReposCourt));
            }
            maNouvelleSeance.add(getValueEnMiliseconds(this.tempsReposLong));
        }

        return maNouvelleSeance;
    }

    public ArrayList<String> seanceTitleCategorie() {
        ArrayList<String> maNouvelleSeance = new ArrayList<String>();

        maNouvelleSeance.add("Préparation");
        for(int i = 0; i < this.sequence ; i++) {
            for(int j =0; j<this.cycle;j++){
                maNouvelleSeance.add("Travail");
                maNouvelleSeance.add("Repos");
            }
            maNouvelleSeance.add("Repos long");
        }

        return maNouvelleSeance;
    }



    public List<Categorie> getSeanceCategories(){

        List<Categorie> categories = new ArrayList<>();

        categories.add(new Categorie("Préparation", getTempsEntrainement()));
        categories.add(new Categorie("Travail", getTempsTravail()));
        categories.add(new Categorie("Repos", getTempsReposCourt()));
        categories.add(new Categorie("Repos long", getTempsReposLong()));
        categories.add(new Categorie("Sequence", getSequence()));
        categories.add(new Categorie("Cycle", getCycle()));

        return categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempsEntrainement() {
        return tempsEntrainement;
    }

    public void setTempsEntrainement(int tempsEntrainement) {
        this.tempsEntrainement = tempsEntrainement;
    }

    public int getTempsTravail() {
        return tempsTravail;
    }

    public void setTempsTravail(int tempsTravail) {
        this.tempsTravail = tempsTravail;
    }

    public int getTempsReposCourt() {
        return tempsReposCourt;
    }

    public void setTempsReposCourt(int tempsReposCourt) {
        this.tempsReposCourt = tempsReposCourt;
    }

    public int getTempsReposLong() {
        return tempsReposLong;
    }

    public void setTempsReposLong(int tempsReposLong) {
        this.tempsReposLong = tempsReposLong;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getValueEnMiliseconds(int entier) {
        return entier*1000;
    }
}
