package com.lp.projethiit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;

import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    String[] tabCategories = {"Temps entrainement", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};
    /** Called when the activity is first created. */
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // create an array of Strings, that will be put to our ListActivity
        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, getModel());
        setListAdapter(adapter);
       // setContentView(R.layout.activity_main);
    }

    private List<Categorie> getModel() {
        List<Categorie> list = new ArrayList<Categorie>();
        for (int i = 0; i < tabCategories.length ; i++){
            list.add(get(tabCategories[i]));
        }
        return list;
    }

    private Categorie get(String s) {
        return new Categorie(s, 10);
    }

}
/*
public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private int tempsTravail = 10;
    String[] tabCategories = {"Temps entrainement", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};
    private List<Categorie> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        for (int i = 0; i < tabCategories.length ; i++){

            // on doit lier R.layout.nomFichierTemplate
            LinearLayout linearTPM = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_template_seance, null);

            Categorie cat = new Categorie(tabCategories[i], 10);
            Button btnLessC;
            btnLessC = (Button) linearTPM.findViewById(R.id.btnLess);
            btnLessC.setTag(cat);
            btnLessC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Marie", "on click");
                    Categorie cat = (Categorie)view.getTag();
                    cat.decrement();
                }});
            categories.add(cat);
            //Log.d("test", linearTPM);
            //Log.d("test", "je rentre ici");

            mainLayout.addView(linearTPM);
        }

        Button goToChrono = new Button(this);
       goToChrono.setText("Let's go!");
       mainLayout.addView(goToChrono);

        goToChrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Marie", "on click");
                goToChrono();
            }
        });

    }

 public void actionBtnAddTravail(View view) {
        //Log.d("Marie", "ActionBtnAdd");
        //Log.d("Marie",String.valueOf(tempsTravail));

     //for (String categorie:tabCategories) {

        // if(categorie == btnAdd.getTag().toString()) {
             //Log.d("categorie.getText()", categorie.getText().toString());
           //  Log.d("tutu", btnLessC.getTag().toString());
             tempsTravail = tempsTravail + 1;
             afficheTempsTravail.setText(String.valueOf(tempsTravail));
        // }
    // }

    }

    public void actionBtnLessTravail(View view) {

       /* if(categorie.getText().toString() == btnLessC.getTag().toString()) {
            tempsTravail = tempsTravail - 1;
            afficheTempsTravail.setText(tempsTravail);
        }
    }

    public void goToChrono() {
        Intent pageChrono = new Intent(this, ChronoActivity.class);
        pageChrono.putExtra("temps_travail", tempsTravail * 1000);
        startActivity(pageChrono);
    }
}*/
