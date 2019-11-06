package com.lp.projethiit.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lp.projethiit.Bd.Categorie;

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
        setTempsEntrainement(10);
        setTempsTravail(10);
        setTempsReposCourt(10);
        setTempsReposLong(10);
        setSequence(5);
        setCycle(4);
    }

    public void createSeanceAvecCategories(List<Categorie> categories) {


       for (Categorie categorie : categories) {
           if (categorie.getTitle().equals("Préparation")) {
               this.tempsEntrainement = categorie.getValue();
           }
           if (categorie.getTitle().equals("Travail")) {
               this.tempsEntrainement = categorie.getValue();
           }
           if (categorie.getTitle().equals("Repos")) {
               this.tempsEntrainement = categorie.getValue();
           }
           if (categorie.getTitle().equals("Repos long")) {
               this.tempsEntrainement = categorie.getValue();
           }
           if (categorie.getTitle().equals("Sequence")) {
               this.tempsEntrainement = categorie.getValue();
           }
           if (categorie.getTitle().equals("Cycle")) {
               this.tempsEntrainement = categorie.getValue();
           }
       }


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
}
