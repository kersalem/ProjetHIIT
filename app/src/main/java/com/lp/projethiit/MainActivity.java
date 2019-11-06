package com.lp.projethiit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Adapter.CategorieAdapter;
import com.lp.projethiit.Bd.Categorie;
import com.lp.projethiit.Model.Seance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] tabCategories = {"Preparation", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};


    private List<Categorie> categories = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer ListView du main activity xml
        ListView timeList = findViewById(R.id.timeList);

        categories = CreateCategoriesInLocalAndReturnIt(tabCategories);

        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);

        timeList.setAdapter(adapter);
        Button btnValider = findViewById(R.id.btnValider);

        //action bouton
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChrono();
            }
        });
    }


    //On passe "seance" à l'activité Chrono
    private void goToChrono() {

        // Créer la seance
        // Seance seance = new Seance(categories)
        Seance seance = new Seance();
        seance.createSeanceAvecCategories(categories);


        // Sauvegarder en base


        // Lancer le chrono
        Intent pageChrono = new Intent(this, ChronoActivity.class);
        pageChrono.putExtra("seance", (Serializable) seance);
        startActivity(pageChrono);
    }

    // On créé une liste de catégorie
    private List<Categorie> CreateCategoriesInLocalAndReturnIt(String[] titleCategories) {


        // Création d'une seance
        Seance seance = new Seance();

        // Retourne la liste des catégories associées à la séance
        return seance.getSeanceCategories();
    }
}
