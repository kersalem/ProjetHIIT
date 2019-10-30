package com.lp.projethiit.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Seance implements Serializable {

    private Categorie tempsEntrainement;
    private Categorie tempsTravail;
    private Categorie tempsReposCourt;
    private Categorie tempsReposLong;
    private Categorie sequence;
    private Categorie cycle;

    public void createSeanceAvecCategories(List<Categorie> sequences) {


        this.tempsEntrainement = sequences.get(0);
        this.tempsTravail = sequences.get(1);
        this.tempsReposCourt = sequences.get(2);
        this.tempsReposLong = sequences.get(3);
        this.sequence = sequences.get(4);
        this.cycle = sequences.get(5);
    }

    public List<Categorie> getSeanceCategories(){
        List<Categorie> myList = new ArrayList<>();
        myList.add(tempsEntrainement);

        for(int i = 0; i < sequence.getValue() ; i++) {
            for(int j =0; j<cycle.getValue();j++){
                myList.add(tempsTravail);
                myList.add(tempsReposCourt);
            }
            myList.add(tempsReposLong);
        }

        return myList;
    }
}
