package com.lp.projethiit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] tabCategories = {"Temps entrainement", "Travail", "Repos court", "Repos long", "Sequence", "cycle"};
    List<Categorie> categories;
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create an array of Strings, that will be put to our ListActivity
        ListView timeList = findViewById(R.id.timeList);
        categories = getModel();
        ArrayAdapter<Categorie> adapter = new CategorieAdapter(this, categories);
        timeList.setAdapter(adapter);
        Button btnValider = findViewById(R.id.btnValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChrono();
            }
        });
    }

    private void goToChrono() {
        Intent pageChrono = new Intent(this, ChronoActivity.class);
        pageChrono.putExtra("categories", (Serializable) categories);
        startActivity(pageChrono);
    }

    private List<Categorie> getModel() {
        List<Categorie> list = new ArrayList<Categorie>();
        for (int i = 0; i < tabCategories.length; i++) {
            list.add(get(tabCategories[i]));
        }
        return list;
    }

    private Categorie get(String s) {
        return new Categorie(s, 10);
    }

}
