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

    private String name;

    private int tempsEntrainement;

    private int tempsTravail;

    private int tempsReposCourt;

    private int tempsReposLong;

    private int sequence;

    private int cycle;

    public void creationSeance(List<Categorie> categories) {


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


    public ArrayList<Categorie> createSeance() {
        ArrayList<Categorie> maNouvelleSeance = new ArrayList<>();

        maNouvelleSeance.add(new Categorie("Préparation", getValueEnMiliseconds(this.tempsEntrainement)));


        for(int i = 0; i < this.sequence ; i++) {
            for(int j =0; j<this.cycle;j++){

                maNouvelleSeance.add(new Categorie("Travail", getValueEnMiliseconds(this.tempsTravail)));

                if(j != this.cycle-1){
                    maNouvelleSeance.add(new Categorie("Repos", getValueEnMiliseconds(this.tempsReposCourt)));
                }
            }
            maNouvelleSeance.add(new Categorie("Repos long", getValueEnMiliseconds(this.tempsReposLong)));
        }

        return maNouvelleSeance;
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
        this.tempsTravail=tempsTravail;
    }

    public int getTempsReposCourt() {
        return tempsReposCourt;
    }

    public void setTempsReposCourt(int tempsReposCourt) {
        this.tempsReposCourt= tempsReposCourt;
    }

    public int getTempsReposLong() {
        return tempsReposLong;
    }

    public void setTempsReposLong(int tempsReposLong) {
        this.tempsReposLong=tempsReposLong;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
