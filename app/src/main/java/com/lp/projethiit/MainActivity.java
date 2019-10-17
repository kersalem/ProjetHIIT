package com.lp.projethiit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private int tempsTravail = 10;
    TextView afficheTempsTravail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        for (int i = 1; i < 6 ; i++){

            // on doit lier R.layout.nomFichierTemplate
            LinearLayout linearTPM = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_template_seance, null);

           // Button btnAdd = (Button) linearTPM.findViewById(R.id.btnAdd);
            //btnAdd.setText("+");

            //Button btnLess = (Button) linearTPM.findViewById(R.id.btnLess);
            //btnAdd.setText("-");


            TextView categorie = (TextView) linearTPM.findViewById(R.id.categorie);
            categorie.setText("Travail");
            categorie.getText().toString();


            afficheTempsTravail = (TextView) linearTPM.findViewById(R.id.afficheTempsTravail);

            afficheTempsTravail.setText("Vous avez choisi " +  String.valueOf(tempsTravail));

            mainLayout.addView(linearTPM);
            //Log.d("test", linearTPM);
            Log.d("test", "je rentre ici");

        }
        Button goToChrono = new Button(this);
       goToChrono.setText("Let's go!");
       mainLayout.addView(goToChrono);


    }

    public void actionBtnAddTravail(View view) {
        Log.d("Marie", "ActionBtnAdd");
        Log.d("Marie",String.valueOf(tempsTravail));

        tempsTravail = tempsTravail + 1;

        afficheTempsTravail.setText("Vous avez choisi " + tempsTravail);
    }

    public void actionBtnLessTravail(View view) {
        tempsTravail = tempsTravail - 1;
        afficheTempsTravail.setText("Vous avez choisi " + tempsTravail);
    }

    public void goToChrono(View view) {
        Intent pageChrono = new Intent(this, ChronoActivity.class);
        pageChrono.putExtra("temps_travail", tempsTravail * 1000);
        startActivity(pageChrono);
    }
}
