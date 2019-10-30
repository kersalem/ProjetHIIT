package com.lp.projethiit;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lp.projethiit.Model.Categorie;

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
    private List<Integer>seance;


    List<Integer> myList = new ArrayList<>();

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

        sequences = (List<Categorie>)getIntent().getSerializableExtra("sequences");
         //afficheTempsTravail = (TextView) findViewById(R.id.afficheTempsTravail);
        //nomActivite = (TextView) findViewById(R.id.nomActivite);
        seance = createSeance(sequences);

        updatedTime = seance.get(position);

        //nomActivite.setText("nom de l'activite : "+ sequences.get(position).getTitle());
        //afficheTempsTravail.setText("Le temps de travail choisi est : " + sequences.get(position).getValue());
        //System.out.println("item????????" + sequences.get(0).getTitle());
/*
        int item1 = sequences.get(0).getValue();
        int item2 = sequences.get(1).getValue();
        int item3 = sequences.get(2).getValue();
        int item4 = sequences.get(3).getValue();
        int item5 = sequences.get(4).getValue();
        int item0 = sequences.get(5).getValue();
        System.out.println("item????????" + item0 + " " + item1 + " " +  item2 + " " + item3 + " " + item4 + " " + item5);
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
                position++;
                updatedTime = sequences.get(position).getValue() * 1000;
                nomActivite.setText("nom de l'activite : "+ sequences.get(position).getTitle());
                afficheTempsTravail.setText("Le temps de travail choisi est : " + sequences.get(position).getValue());
                timer.start();
                miseAJour();
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


        // Mise à jour graphique
        miseAJour();

    }

    private List<Integer> createSeance(List<Categorie> sequences) {
        List<Integer> myList = new ArrayList<>();


        int tempsEntrainement = sequences.get(5).getValue();
        int tempsTravail = sequences.get(0).getValue();
        int tempsReposCourt = sequences.get(1).getValue();
        int tempsReposLong = sequences.get(2).getValue();
        int sequence = sequences.get(3).getValue();
        int cycle = sequences.get(4).getValue();

        myList.add(tempsEntrainement * 1000);

        for(int i = 0; i < sequence ; i++) {
            for(int j =0; j<cycle;j++){
                myList.add(tempsTravail * 1000);
                myList.add(tempsReposCourt * 1000);
            }
            myList.add(tempsReposLong * 1000);
        }

        return myList;
    }
}
