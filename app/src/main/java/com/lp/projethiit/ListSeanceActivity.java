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

        seance = (Seance) getIntent().getSerializableExtra("seance");
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
                //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                // enregistrement dans la prochaine activity List

                for (Seance s : seances) {
                    LinearLayout linearLine = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_list_template, null);

                    //TextView etapeName = (TextView) linearLine.findViewById(R.id.seanceName);
                    //TextView seanceTemps = (TextView) linearLine.findViewById(R.id.seanceTemps);

                    ((TextView) linearLine.findViewById(R.id.list_nb_sequence)).setText("Sequence : " + s.getSequence());
                    ((TextView) linearLine.findViewById(R.id.list_nb_cycle)).setText("Cycle : " + s.getCycle());
                    ((TextView) linearLine.findViewById(R.id.list_temps_travail)).setText("Travail : " + s.getTempsTravail());
                    ((TextView) linearLine.findViewById(R.id.list_temps_repos)).setText("Repos : " + s.getTempsReposCourt());
                    Button btnJouerSeance = (Button) linearLine.findViewById(R.id.btnJouerSeance);


                    btnJouerSeance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("Marie", "on click btnJouerSeance");
                            selectionnerCetteSeance();
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
    private void selectionnerCetteSeance() {
        Log.d("Marie", "selectionnerCetteSeance");

        Intent seanceChoisie = new Intent(this, ChronoActivity.class);
        seanceChoisie.putExtra("seance", (Serializable) seance);
        startActivity(seanceChoisie);
    }

}
