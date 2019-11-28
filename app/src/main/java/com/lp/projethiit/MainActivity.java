package com.lp.projethiit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Adapter.CategorieAdapter;
import com.lp.projethiit.Bd.DatabaseClient;
import com.lp.projethiit.Bd.Seance;
import com.lp.projethiit.Model.Categorie;
import com.lp.projethiit.Model.DefaultCategories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;
    private Button saveView;
    private Button btnSelectSeance;

    Seance seance = new Seance();

    //Son
/*    private SoundPool soundPool;
    private AudioManager audioManager;*/

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

        categories = new DefaultCategories().getCategories();

        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);

        timeList.setAdapter(adapter);
        Button btnEnregistrer = findViewById(R.id.btnValider);

        //action bouton
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBtnJouerSeance();
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

        btnSelectSeance = findViewById(R.id.btnSelectSeance);

        btnSelectSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBtnChoisirSeance();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        Toast.makeText(getApplicationContext(), "Portrait mode", Toast.LENGTH_SHORT).show();
        } else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(getApplicationContext(), "landscape mode", Toast.LENGTH_SHORT).show();

        }
    }

    private void saveSeance() {

        //Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
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
    private void actionBtnJouerSeance() {

        // Créer la seance
        Seance seance = new Seance();
        seance.creationSeance(categories);

        // Direction chrono activity
        Intent pageChrono = new Intent(this, ChronoActivity.class);
        pageChrono.putExtra("seance", (Serializable) seance);
        startActivity(pageChrono);
    }

    //Direction ListSeances activity
    private void actionBtnChoisirSeance() {

        Intent pageListSeances = new Intent(this, ListSeanceActivity.class);
        startActivity(pageListSeances);
    }

    // On créé une liste de catégorie
}
