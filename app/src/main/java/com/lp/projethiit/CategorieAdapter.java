package com.lp.projethiit;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CategorieAdapter extends ArrayAdapter<Categorie> {
    private final Activity context;
    private final List<Categorie> categories;

    static class ViewHolder {
        public TextView text;
        public Button btnLess;
        public Button btnAdd;
        public TextView result;

    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public CategorieAdapter(Activity context, List<Categorie> categories ) {
        super(context, R.layout.activity_template_seance, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
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
                    Log.d("Marie", "on click Decrement");
                    Categorie cat = categories.get(position);
                    cat.decrement();
                    viewHolder.result.setText(Integer.toString(cat.getValue()));
                }});

            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Marie", "on click increment");
                    Categorie cat = categories.get(position);
                    cat.increment();
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


