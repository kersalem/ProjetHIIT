package com.lp.projethiit.Adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lp.projethiit.Model.Categorie;
import com.lp.projethiit.R;

import java.util.List;

public class CategorieAdapter extends ArrayAdapter<Categorie> {
    private final Activity context;
    private final List<Categorie> categories;

    // on défini la classe ViewHolder avec les éléments qui feront une ligne
    static class ViewHolder {
        public TextView text;
        public Button btnLess;
        public Button btnAdd;
        public TextView result;
    }

    // constructor
    public CategorieAdapter(Activity context, List<Categorie> categories ) {
        // super = appeler la classe que j'étends - ligne 18
        super(context, R.layout.activity_template_seance, categories);
        this.context = context;
        this.categories = categories;
    }

    // ecraser l'implémentation de la fonction getView() de arrayAdapter
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        // Regarde si la view(une ligne) existe déjà
        // convertView = view en cours de traitement(affichage dans lecran
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_template_seance, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.title);
            viewHolder.btnAdd = (Button) view.findViewById(R.id.btnAdd);
            viewHolder.result = (TextView) view.findViewById(R.id.result);
            viewHolder.btnLess = (Button) view.findViewById(R.id.btnLess);

            viewHolder.btnLess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // recup modele = Categorie - categories
                    // categories[position]
                    Categorie cat = categories.get(position);
                    cat.Decrement();
                    viewHolder.result.setText(Integer.toString(cat.getValue()));
                }});

            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Categorie cat =categories.get(position);
                    cat.Increment();
                    viewHolder.result.setText(Integer.toString(cat.getValue()));
                }});

            viewHolder.text.setText(categories.get(position).getTitle());
            viewHolder.result.setText(Integer.toString(categories.get(position).getValue()));
            view.setTag(viewHolder);

        } else {
            view = convertView;
        }
        return view;
    }
}


