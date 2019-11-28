package com.lp.projethiit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Bd.DatabaseClient;
import com.lp.projethiit.Bd.Seance;

import java.io.Serializable;
import java.util.List;

public class ListSeanceActivity extends AppCompatActivity {
    private DatabaseClient mDb;
    Seance seance;
    private LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_seance);

        list = findViewById(R.id.list);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        getSeance();
    }



    /// a mettre dzns liste activités
    private void getSeance() {
        class GetSeance extends AsyncTask<Void, Void, List<Seance>> {

            @Override
            protected List<Seance> doInBackground(Void... voids) {

                List<Seance> list = mDb.getAppDatabase()
                        .SeanceDao()
                        .getAll();


                return list;
            }

            @Override
            protected void onPostExecute(List<Seance> seances) {
                super.onPostExecute(seances);
                //update(seances);

                for (Seance s : seances) {
                    final Seance seanceToPass = s;
                    LinearLayout linearLine = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_list_template, null);

                    ((TextView) linearLine.findViewById(R.id.list_nb_sequence)).setText("Entreprise : " + seanceToPass.getTempsEntrainement());
                    ((TextView) linearLine.findViewById(R.id.list_nb_cycle)).setText("Cycle : " + seanceToPass.getCycle());
                    ((TextView) linearLine.findViewById(R.id.list_temps_travail)).setText("Travail : " + seanceToPass.getTempsTravail());
                    ((TextView) linearLine.findViewById(R.id.list_temps_repos)).setText("Repos : " + seanceToPass.getTempsReposCourt());
                    Button btnJouerSeance = (Button) linearLine.findViewById(R.id.btnJouerSeance);


                    btnJouerSeance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("Marie", "on click btnJouerSeance");
                            selectionnerCetteSeance(seanceToPass);
                        }
                    });


                    list.addView(linearLine);
                }

            }
        }

        GetSeance getSeance = new GetSeance();
        getSeance.execute();
    }

    public void jouerSeance(View view){
        Intent intent = new Intent(this, ChronoActivity.class);
    }

    //Direction activité Chrono
    private void selectionnerCetteSeance(Seance seance) {
        Log.d("Marie", "selectionnerCetteSeance");

        Intent seanceChoisie = new Intent(this, ChronoActivity.class);
        seanceChoisie.putExtra("seance", (Serializable) seance);
        startActivity(seanceChoisie);
    }

}
