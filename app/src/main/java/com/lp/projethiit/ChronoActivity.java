package com.lp.projethiit;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Bd.Categorie;
import com.lp.projethiit.Model.Seance;

import java.util.ArrayList;
import java.util.List;

public class ChronoActivity extends AppCompatActivity {

    // CONSTANTE
    private final static long INITIAL_TIME = 5000;

    // VIEW
    private Button startButton;
    private Button pauseButton;
    private TextView timerValue;
    TextView afficheTempsTravail;
    // DATA
    int position;
    private long updatedTime;
    private CountDownTimer timer;
    List<Categorie> sequences;
    private TextView nomActivite;
    //propri de class
    Seance seance;
    private ArrayList<Integer> sequencesDanslordre;
    private ArrayList<String> sequenceTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        position = 0;
        // Récupérer les view
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);

        //Récupération temps de travail choisi

        seance = (Seance)getIntent().getSerializableExtra("seance");
        afficheTempsTravail = (TextView) findViewById(R.id.afficheTempsTravail);
        nomActivite = (TextView) findViewById(R.id.nomActivite);

        //sequencesDanslordre = seance.getSeanceCategories();

        sequencesDanslordre = seance.createMaNouvelleSeance();
        sequenceTitre = seance.seanceTitleCategorie();

        updatedTime = sequencesDanslordre.get(position);



        nomActivite.setText("nom de l'activite : "+ sequenceTitre.get(position));
        afficheTempsTravail.setText("Le temps de travail choisi est : " + sequencesDanslordre.get(position));
        //System.out.println("item????????" + sequences.get(0).getTitle());

        /*
        System.out.println("eejuk;,yj,yi;k");
        System.out.println(seance.getTempsEntrainement());
        System.out.println(seance.getTempsReposCourt());
        System.out.println(seance.getTempsReposLong());
        System.out.println(seance.getTempsTravail());
        */

        miseAJour();
    }

    /**
     * ATTENTION L'utilisateur peut créer plusieurs CountDownTimer !!!
     * -> Pensez à faire tester votre application par un tiers
     *
     * @param view
     */
    public void onStart(View view) {

        timer = new CountDownTimer(updatedTime, 10) {

            public void onTick(long millisUntilFinished) {
                updatedTime = millisUntilFinished;
                miseAJour();
            }

            public void onFinish() {
                updatedTime = 0;
                if(position < sequencesDanslordre.size() -1) {
                    position++;
                    updatedTime = sequencesDanslordre.get(position);
                    afficheTempsTravail.setText("Le temps de toto choisi est : " + sequencesDanslordre.get(position));
                    nomActivite.setText("activite toto : " + sequenceTitre.get(position));

                    miseAJour();
                    onStart();
                }else{
                    Log.d("test", "finiiiiiiiiiiiiiiiiiiiiiiiiiii");
                }
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

    // Remettre à le compteur à la valeur initiale
    public void onReset(View view) {

        // Mettre en pause
        if (timer != null) {
            timer.cancel();
        }

        // Réinitialiser
        position = 0;
        updatedTime = sequencesDanslordre.get(position);

        // Mise à jour graphique
        miseAJour();

    }




}
