package com.lp.projethiit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Bd.DatabaseClient;
import com.lp.projethiit.Bd.Seance;

import java.util.List;

public class ListSeanceActivity extends AppCompatActivity {
    private DatabaseClient mDb;
    Seance seance;
    LinearLayout list;

    //private Button btnJouerSeance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_template);

        seance = (Seance) getIntent().getSerializableExtra("seance");
        //btnJouerSeance = findViewById(R.id.btnJouerSeance);


        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        //action bouton
/*
        btnJouerSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectionnerCetteSeance();
            }
        });
*/


        getSeance();
    }

    //Direction activité Chrono
/*    private void selectionnerCetteSeance() {
        Intent seanceChoisie = new Intent(this, ChronoActivity.class);
        //seanceChoisie.putExtra("seance", (Serializable) seance);
        startActivity(seanceChoisie);
    }*/

    /// a mettre dzns liste activités
    private void getSeance() {
        class GetSeance extends AsyncTask<Void, Void, List<Seance>> {

            @Override
            protected List<Seance> doInBackground(Void... voids) {

                List<Seance> seanceList = mDb.getAppDatabase()
                        .SeanceDao()
                        .getAll();


                return seanceList;
            }

            @Override
            protected void onPostExecute(List<Seance> seances) {
                super.onPostExecute(seances);
                //update(seances);
                //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                // enregistrement dans la prochaine activity List

                for (Seance s : seances) {
                    LinearLayout linearLine = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_list_template, null);

                    TextView seanceName = (TextView) linearLine.findViewById(R.id.seanceName);
                    TextView seanceTemps = (TextView) linearLine.findViewById(R.id.seanceTemps);

                    seanceName.setText("NB sequence : " + s.getSequence());
                    seanceName.setText("NB cycle : " + s.getCycle());
                    seanceName.setText("NB travail : " + s.getTempsTravail());
                    seanceName.setText("NB repos : " + s.getTempsReposCourt());

                   list = (LinearLayout) findViewById(R.id.lineList);
                   list.addView(linearLine);

                }
            }
        }

        GetSeance getSeance = new GetSeance();
        getSeance.execute();
    }

}
