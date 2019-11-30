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

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    // VIEW
    private EditText editNameSeance;

    // DATA
    private DatabaseClient mDb;
    Seance seance = new Seance();



    private List<Categorie> categories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button saveView;
        seance = new Seance();
        Button btnListSeance;

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        editNameSeance = (EditText) findViewById(R.id.editNameSeance);


        // Récupérer ListView du main activity xml
        ListView timeList = findViewById(R.id.timeList);

        saveView = findViewById(R.id.button_save);

        categories = new DefaultCategories().getCategories();

        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);

        timeList.setAdapter(adapter);

        //enregistrer update seance
        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSeance();
            }
        });

        // Action bouton List seance
        btnListSeance = findViewById(R.id.btnListSeance);

        btnListSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retourListeSeance();
            }
        });

    }


    //update
    private void updateSeance() {

        seance.setName(editNameSeance.getText().toString());

        seance.setCategories(categories);
        //Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
        class SaveSeance extends AsyncTask<Void, Void, Seance> {

            @Override
            protected Seance doInBackground(Void... voids) {

                // adding to database

                    mDb.getAppDatabase()
                            .SeanceDao()
                            .update(seance);
                    return seance;
            }

            @Override
            protected void onPostExecute(Seance seance) {
                super.onPostExecute(seance);

                setResult(RESULT_OK);
                Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_LONG).show();
            }
        }

        SaveSeance ss = new SaveSeance();
        ss.execute();

    }


    //Direction ListSeances activity
    private void retourListeSeance() {

        Intent pageListSeances = new Intent(this, ListSeanceActivity.class);
        startActivity(pageListSeances);
    }

}

