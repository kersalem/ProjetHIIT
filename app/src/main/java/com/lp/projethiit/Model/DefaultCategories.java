package com.lp.projethiit.Model;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategories {


    public List<Categorie> getCategories(){

        List<Categorie> categories = new ArrayList<>();

        categories.add(new Categorie("Préparation", 6));
        categories.add(new Categorie("Travail",2));
        categories.add(new Categorie("Repos", 3));
        categories.add(new Categorie("Repos long", 4));
        categories.add(new Categorie("Sequence", 2));
        categories.add(new Categorie("Cycle", 2));

        return categories;
    }

}
