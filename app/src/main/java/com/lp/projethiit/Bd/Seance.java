package com.lp.projethiit.Bd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lp.projethiit.Model.Categorie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "seances")
public class Seance implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "description")
    private String description;

    private int tempsEntrainement;

    private int tempsTravail;

    private int tempsReposCourt;

    private int tempsReposLong;

    private int sequence;

    private int cycle;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Seance(String color) {
        this.color = color;
    }

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

    public void creationSeance(List<Categorie> categories) {


       for (Categorie categorie : categories) {
           if (categorie.getTitle().equals("Préparation")) {
               this.tempsEntrainement = categorie.getValue();
               this.setColor("#FF6347");

           }
           if (categorie.getTitle().equals("Travail")) {
               this.tempsTravail = categorie.getValue();
               this.setColor("#FF6347");
           }
           if (categorie.getTitle().equals("Repos")) {
               this.tempsReposCourt = categorie.getValue();
               this.setColor("#FF6347");
           }
           if (categorie.getTitle().equals("Repos long")) {
               this.tempsReposLong = categorie.getValue();
               this.setColor("#FF6347");
           }
           if (categorie.getTitle().equals("Sequence")) {
               this.sequence = categorie.getValue();
               this.setColor("#FF6347");
           }
           if (categorie.getTitle().equals("Cycle")) {
               this.cycle = categorie.getValue();
               this.setColor("#FF6347");
           }
       }

    }


public ArrayList<Integer> creerNouvelleSeance() {
ArrayList<Integer> maNouvelleSeance = new ArrayList<Integer>();

    maNouvelleSeance.add(getValueEnMiliseconds(this.tempsEntrainement));
        for(int i = 0; i < this.sequence ; i++) {
            for(int j =0; j<this.cycle;j++){
                maNouvelleSeance.add(getValueEnMiliseconds(this.tempsTravail));

                if(j != this.cycle-1){
                    maNouvelleSeance.add(getValueEnMiliseconds(this.tempsReposCourt));
                }
            }
            maNouvelleSeance.add(getValueEnMiliseconds(this.tempsReposLong));
        }

        return maNouvelleSeance;
    }

    public ArrayList<String> ajouterTitreEtape() {
        ArrayList<String> maNouvelleSeance = new ArrayList<String>();

        maNouvelleSeance.add("Préparation");
        for(int i = 0; i < this.sequence ; i++) {
            for(int j =0; j<this.cycle;j++){
                maNouvelleSeance.add("Travail");

                if(j != this.cycle-1){
                    maNouvelleSeance.add("Repos");
                }
            }
            maNouvelleSeance.add("Repos long");
        }

        return maNouvelleSeance;
    }



    public List<Categorie> getSeance(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
