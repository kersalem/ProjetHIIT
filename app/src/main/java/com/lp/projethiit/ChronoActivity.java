package com.lp.projethiit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Bd.Seance;

import java.util.ArrayList;
public class ChronoActivity extends AppCompatActivity {

/*
    // CONSTANTE
    private final static long INITIAL_TIME = 5000;
*/

    // VIEW
    private Button startButton;
    private Button pauseButton;
    private TextView timerValue;
    private TextView afficheTempsTravail;
    private TextView nomActivite;
    private Seance seance;

    //DATA
    private CountDownTimer timer;
    private ArrayList<Integer> sequenceEnCours;
    private ArrayList<String> sequenceTitre;
    private long updatedTime;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        seance = new Seance();
        position = 0;

        // Récupérer les view
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        afficheTempsTravail = (TextView) findViewById(R.id.afficheTempsTravail);
        nomActivite = (TextView) findViewById(R.id.nomActivite);

        // Récupération intention
        seance = (Seance)getIntent().getSerializableExtra("seance");
        sequenceEnCours = seance.creerNouvelleSeance();
        sequenceTitre = seance.ajouterTitreEtape();

        updatedTime = sequenceEnCours.get(position);

        //Affichage Titre et Temps étape
        nomActivite.setText(sequenceTitre.get(position));
        afficheTempsTravail.setText(sequenceEnCours.get(position) / 1000 + " s");

        miseAJour();
        //onStart();
    }

    public void onStart(View view) {
        startChrono();
    }

    public void startChrono() {
        timer = new CountDownTimer(updatedTime, 10) {

            public void onTick(long millisUntilFinished) {
                updatedTime = millisUntilFinished;
                miseAJour();
            }

            public void onFinish() {
                updatedTime = 0;
                miseAJour();
                if(position < sequenceEnCours.size()-1) {
                    position++;
                    updatedTime = sequenceEnCours.get(position);
                    afficheTempsTravail.setText(sequenceEnCours.get(position)/ 1000 + " s");
                    nomActivite.setText(sequenceTitre.get(position));
                    miseAJour();
                }/*else{
                    Log.d("test", "finiiiiiiiiiiiiiiiiiiiiiiiiiii");
                }*/
            }
        }.start();
    }

    // Mettre en pause le compteur
    public void onPause(View view) {
        if (timer != null) {
            timer.cancel(); // Arrete le CountDownTimer
        }
    }


    // Mise à jour graphique
    private void miseAJour() {

        // Décompositions en secondes et minutes
        int secs = (int) (updatedTime / 1000);
        int mins = secs / 60;
        secs = secs % 60;
        int milliseconds = (int) (updatedTime % 1000);

        // Affichage du "timer"
        timerValue.setText("" + mins + ":"
                + String.format("%02d", secs) + ":"
                + String.format("%03d", milliseconds));
    }

    // Remettre compteur à la valeur initiale de l'étape en cours
    public void onReset(View view) {

        // Mettre en pause
        if (timer != null) {
            timer.cancel();
        }

        // Réinitialiser
        position = 0;
        updatedTime = sequenceEnCours.get(position);

        // Mise à jour graphique
        miseAJour();
    }
}
