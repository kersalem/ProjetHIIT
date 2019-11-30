package com.lp.projethiit;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Bd.Seance;
import com.lp.projethiit.Model.Categorie;

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
    private LinearLayout linearGlobal;
    private Categorie categorie;
    private MediaPlayer siffletFinEtape;
    private MediaPlayer siffletFinSeance;
    private boolean isRunning = false;
    //DATA
    private CountDownTimer timer;
    private ArrayList<Categorie> seanceEnCours;
    private long updatedTime;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        seance = new Seance();
        position = 0;

        // Récupérer les view
        linearGlobal = (LinearLayout) findViewById(R.id.activity_chrono);
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        afficheTempsTravail = (TextView) findViewById(R.id.afficheTempsTravail);
        nomActivite = (TextView) findViewById(R.id.nomActivite);

        // Récupération intention
        seance = (Seance)getIntent().getSerializableExtra("seance");
  /*    sequenceEnCours = seance.creerNouvelleSeance();
        sequenceTitre = seance.ajouterTitreEtape();*/
        seanceEnCours = seance.createSeance();

        categorie = seanceEnCours.get(position);


        updatedTime = categorie.getValue();

        //Affichage Titre et Temps étape
        nomActivite.setText(categorie.getTitle());
        afficheTempsTravail.setText(updatedTime / 1000 + " s");

        //Paramétrer sons
         siffletFinEtape = MediaPlayer.create(this, R.raw.sifflet);
         siffletFinSeance = MediaPlayer.create(this, R.raw.sifflet3);

        miseAJour();
    }


    public void onStart(View view) {
        startChrono();
    }

    public void startChrono() {

        if(timer != null && isRunning){
            return;
        }

        timer = new CountDownTimer(updatedTime, 10) {

            public void onTick(long millisUntilFinished) {
                updatedTime = millisUntilFinished;
                miseAJour();
            }

            public void onFinish() {
                isRunning = false;
                updatedTime = 0;
                miseAJour();
                if(position < seanceEnCours.size()-1) {
                    siffletFinEtape.start();
                    position++;
                    categorie = seanceEnCours.get(position);
                    updatedTime = categorie.getValue();

                    afficheTempsTravail.setText(categorie.getValue() + " s");
                    nomActivite.setText(categorie.getTitle());

                    miseAJour();
                    startChrono();
                }else{
                    nomActivite.setText("Bravo");
                    afficheTempsTravail.setText("seance terminée");
                    linearGlobal.setBackgroundColor(Color.parseColor("#00574B"));
                    siffletFinSeance.start();
                }
            }
        }.start();
        isRunning= true;
    }

    // Mettre en pause le compteur
    public void onPause(View view) {
        if (timer != null) {
            isRunning = false;
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

        setColorBackground();
    }

    public void setColorBackground(){
        switch(categorie.getTitle()) {
            case "Préparation":
                linearGlobal.setBackgroundColor(Color.parseColor("#9966FF"));
                break;

            case "Travail":
                linearGlobal.setBackgroundColor(Color.parseColor("#6666FF"));
                break;

            case "Repos" :
                linearGlobal.setBackgroundColor(Color.parseColor("#6699FF"));
                break;
            case "Repos long" :
                linearGlobal.setBackgroundColor(Color.parseColor("#66FF99"));
                break;
            default:
        }
    }


    // Remettre compteur à la valeur initiale de l'étape en cours
    public void onReset(View view) {

        // Mettre en pause
        if (timer != null) {
            timer.cancel();
        }

        // Réinitialiser
        position = 0;
        updatedTime = categorie.getValue();

        // Mise à jour graphique
        miseAJour();
    }
}
