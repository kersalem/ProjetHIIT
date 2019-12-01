package com.lp.projethiit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    static final String STATE_SEANCE = "seance";

    // VIEW
    private EditText editNameSeance;

    // DATA
    private DatabaseClient mDb;
    private Seance seance = new Seance();
    private List<Categorie> categories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveView;
        Button btnSelectSeance;


        if(savedInstanceState != null) {

            seance = (Seance)savedInstanceState.getSerializable(STATE_SEANCE);
            categories = seance.getCategories();
        } else {

            // On initialise resultat
            seance = new Seance();
            categories = new DefaultCategories().getCategories();

         }


        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        editNameSeance = (EditText) findViewById(R.id.editNameSeance);


        // Récupérer ListView du main activity xml
        ListView timeList = findViewById(R.id.timeList);

         saveView = findViewById(R.id.button_save);


        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);

        timeList.setAdapter(adapter);
        Button btnJouerSeance = findViewById(R.id.btnValider);

        //action bouton
        btnJouerSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSeance();
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
                chooseSeance();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        seance.setCategories(categories);
        outState.putSerializable(STATE_SEANCE, seance );
    }



    private void saveSeance() {

        seance.setName(editNameSeance.getText().toString());

        seance.setCategories(categories);
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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveSeance ss = new SaveSeance();
        ss.execute();

    }


    //On passe "seance" à l'activité Chrono
    private void playSeance() {

        // Créer la seance
        //Seance seance = new Seance();
        seance.setCategories(categories);

        // Direction chrono activity
        Intent pageChrono = new Intent(this, ChronoActivity.class);
        pageChrono.putExtra("seance", (Serializable) seance);
        startActivity(pageChrono);
    }

    //Direction ListSeances activity
    private void chooseSeance() {

        Intent pageListSeances = new Intent(this, ListSeanceActivity.class);
        startActivity(pageListSeances);
    }


}

