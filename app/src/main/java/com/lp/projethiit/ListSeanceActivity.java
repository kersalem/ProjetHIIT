package com.lp.projethiit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Bd.DatabaseClient;
import com.lp.projethiit.Bd.Seance;

import java.io.Serializable;
import java.util.List;

public class ListSeanceActivity extends AppCompatActivity {

    static final String STATE_SEANCE_EDIT = "seanceEdit";

    // VIEW
    private LinearLayout list;

    //DATA
    private DatabaseClient mDb;
    Seance seance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_seance);


        list = findViewById(R.id.list);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        getSeance();
    }

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

                list.removeAllViews();


                for (Seance s : seances) {
                    final Seance seanceToPass = s;
                    LinearLayout linearLine = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_list_template, null);

                    ((TextView) linearLine.findViewById(R.id.list_nom_seance)).setText("Séance : " + seanceToPass.getName());
                    ((TextView) linearLine.findViewById(R.id.list_nb_entrainement)).setText("Temps Entrainement : " + seanceToPass.getTempsEntrainement() + " scdes");
                    ((TextView) linearLine.findViewById(R.id.list_nb_sequence)).setText("Nombre de sequence : " + seanceToPass.getSequence());
                    ((TextView) linearLine.findViewById(R.id.list_nb_cycle)).setText("Nombre de Cycle : " + seanceToPass.getCycle());
                    ((TextView) linearLine.findViewById(R.id.list_temps_travail)).setText("Temps Travail : " + seanceToPass.getTempsTravail() + " scdes");
                    ((TextView) linearLine.findViewById(R.id.list_temps_repos)).setText("Temps Repos : " + seanceToPass.getTempsReposCourt() + " scdes");

                    Button buttonPlaySeance = (Button) linearLine.findViewById(R.id.btnJouerSeance);
                    Button buttonDeleteSeance = (Button) linearLine.findViewById(R.id.btnSupprimerSeance);
                    Button buttonEditSeance = (Button) linearLine.findViewById(R.id.btnEditerSeance);


                    buttonPlaySeance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectSeance(seanceToPass);
                        }
                    });

                    buttonDeleteSeance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteSeanceInList(seanceToPass);

                        }
                    });

                    buttonEditSeance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            editSeance(seanceToPass);

                        }
                    });


                    list.addView(linearLine);
                }

            }
        }

        GetSeance getSeance = new GetSeance();
        getSeance.execute();
    }


    //Direction activité Chrono
    private void selectSeance(Seance seance) {

        Intent chosenSeance = new Intent(this, ChronoActivity.class);
        chosenSeance.putExtra("seance", (Serializable) seance);
        startActivity(chosenSeance);
    }

    /////// EDITER UNE SEANCE
    private void editSeance(Seance seance) {

        Intent editSeance = new Intent(this, EditActivity.class);
        editSeance.putExtra(STATE_SEANCE_EDIT, (Serializable) seance);
        startActivity(editSeance);
    }

    private void deleteSeanceInList(final Seance seance) {

        class DeleteSeance extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                mDb.getAppDatabase()
                        .SeanceDao()
                        .delete(seance);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getSeance();

                setResult(RESULT_OK);
                Toast.makeText(getApplicationContext(), "Suppr", Toast.LENGTH_LONG).show();

            }
        }


        DeleteSeance deleteThisSeance = new DeleteSeance();
        deleteThisSeance.execute();

    }



}
