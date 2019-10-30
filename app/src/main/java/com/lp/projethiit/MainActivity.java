package com.lp.projethiit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.lp.projethiit.Adapter.CategorieAdapter;
import com.lp.projethiit.Model.Categorie;
import com.lp.projethiit.Model.Seance;
import com.lp.projethiit.Utils.TypeSeance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // source de donnée
    // attention ceci n'est pas un modèle
    private String[] tabCategories = {"Temps entrainement", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};
    private String[] tabCategories2 = {"Temps entrainement2", "Trava2il", "Repos court2", "Repos long2", "Sequence2", "cycle2"};

    private List<Categorie> categories = new ArrayList<Categorie>();;



    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer ListView du main activity xml
        ListView timeList = findViewById(R.id.timeList);
        categories = CreateCategoriesInLocalAndReturnIt(tabCategories2);
        //CreateCategoriesInGlobalVariable(tabCategories);
        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);
        // on passe l'adapter crée à cette list de la feuille xml
        timeList.setAdapter(adapter);
        Button btnValider = findViewById(R.id.btnValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChrono();
            }
        });
    }

    private void goToChrono() {
        Intent pageChrono = new Intent(this, ChronoActivity.class);
       // Seance seance = new Seance(categories)
        Seance seance = new Seance();
        seance.createSeanceAvecCategories(categories);
        pageChrono.putExtra("seance", (Serializable) seance);
        startActivity(pageChrono);
    }

    // On créé une liste de catégorie
    private List<Categorie> CreateCategoriesInLocalAndReturnIt(String[] titleCategories) {
        // je crée une nvelle liste de catégorie
        List<Categorie> list = new ArrayList<Categorie>();
        // je boucle sur ma source de donnée
        for (int i = 0; i < titleCategories.length; i++) {
            Categorie maCategorie;
            int initialValue = 3;
            if (titleCategories[i] == "Sequence" || titleCategories[i] == "cycle")
                {
                    // créer categorie nvelle
                    maCategorie = new Categorie(titleCategories[i], initialValue, TypeSeance.Repetition);
                }
            else
                {
                    // créer categorie nvelle
                    maCategorie = new Categorie(titleCategories[i], initialValue, TypeSeance.Temps);
                }
            // Ajouter categorie à la liste
            list.add(maCategorie);


        }
        return list;
    }

  /*  private void CreateCategoriesInGlobalVariable(String[] titleCategories) {

        // je boucle sur ma source de donnée
        for (int i = 0; i < titleCategories.length; i++) {
            Categorie maCategorie;
            if (titleCategories[i] == "Sequence" || titleCategories[i] == "cycle") {
                // créer categorie nvelle
                maCategorie = new Categorie(titleCategories[i], 10, TypeSeance.Repetition);
            } else {
                // créer categorie nvelle
                maCategorie = new Categorie(titleCategories[i], 10, TypeSeance.Temps);
            }
            // Ajouter categorie à la liste
            categories.add(maCategorie);
        }
    } */
}
