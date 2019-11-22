package com.lp.projethiit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Adapter.CategorieAdapter;
import com.lp.projethiit.Bd.Categorie;
import com.lp.projethiit.Bd.DatabaseClient;
import com.lp.projethiit.Bd.Seance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;
    private Button saveView;

    Seance seance = new Seance();
    private String[] tabCategories = {"Preparation", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};


    private List<Categorie> categories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());


        // Récupérer ListView du main activity xml
        ListView timeList = findViewById(R.id.timeList);


         saveView = findViewById(R.id.button_save);

        categories = CreateCategoriesInLocalAndReturnIt(tabCategories);

        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);

        timeList.setAdapter(adapter);
        Button btnValider = findViewById(R.id.btnValider);

        //action bouton
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronoActivity();
            }
        });

        // Associer un événement au bouton save
        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSeance();
            }
        });

        // Action bouton List seance

        Button btnSelectSeance = findViewById(R.id.btnSelectSeance);

        btnSelectSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listSeanceActivity();
            }
        });

    }

    private void saveSeance() {
        /**
         * Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
         */
        class SaveSeance extends AsyncTask<Void, Void, Seance> {


            @Override
            protected Seance doInBackground(Void... voids) {

                // adding to database

                mDb.getAppDatabase()
                        .SeanceDao()
                        .insert(seance);

                return seance;
            }

            @Override
            protected void onPostExecute(Seance seance) {
                super.onPostExecute(seance);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                //finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveSeance ss = new SaveSeance();
        ss.execute();

    }


    //On passe "seance" à l'activité Chrono
    private void chronoActivity() {

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

    //Direction activité ListSeances
    private void listSeanceActivity() {

        Intent pageListSeances = new Intent(this, ListSeanceActivity.class);
        pageListSeances.putExtra("seance", (Serializable) seance);
        startActivity(pageListSeances);
    }

    // On créé une liste de catégorie
    private List<Categorie> CreateCategoriesInLocalAndReturnIt(String[] titleCategories) {


        // Création d'une seance
        Seance seance = new Seance();

        // Retourne la liste des catégories associées à la séance
        return seance.getSeanceCategories();
    }
}
