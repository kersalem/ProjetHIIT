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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // source de donnée
    // attention ceci n'est pas un modèle
    private String[] tabCategories = {"Temps entrainement", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};
    private List<Categorie> categories;



    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer ListView du main activity xml
        ListView timeList = findViewById(R.id.timeList);
        categories = CreateCategories();
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
        pageChrono.putExtra("categories", (Serializable) categories);
        startActivity(pageChrono);
    }

    // On créé une liste de catégorie
    private List<Categorie> CreateCategories() {
        // je crée une nvelle liste de catégorie
        List<Categorie> list = new ArrayList<Categorie>();
        // je boucle sur ma source de donnée
        for (int i = 0; i < tabCategories.length; i++) {
            // créer categorie nvelle
            Categorie maCategorie = CreateCategorie(tabCategories[i]);
            // Ajouter categorie à la liste
            list.add(maCategorie);
        }
        return list;
    }

    // On crée une catégorie que l'on va utiliser dans la création de la Liste de categories
    private Categorie CreateCategorie(String title)
    {
        return new Categorie(title, 10);
    }
}
