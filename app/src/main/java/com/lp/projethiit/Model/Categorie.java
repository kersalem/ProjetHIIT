package com.lp.projethiit.Model;

import android.util.Log;

import com.lp.projethiit.Utils.TypeSeance;

import java.io.Serializable;

// ici on est d ns un modele

// la donnée qui va remplir la ligne
public class Categorie implements Serializable {

    private String _title;
    private int _value;
    private TypeSeance _typeSeance;


    public TypeSeance GetTypeSeance() {
        return _typeSeance;
    }

    private void setTypeSeance(TypeSeance _typeSeance) {
        this._typeSeance = _typeSeance;
    }

    public String getTitle() {
        return _title;
    }
    private void setTitle(String value) {
        this._title = value;
    }

    public int getValue() {
        return _value;
    }

    public int getValueEnMiliseconds() {
        return _value*1000;
    }

    private void setValue(int value) {
        if (value >= 0){
            this._value = value;
        }
    }


    public Categorie(String title, int initialValue, TypeSeance typeSeance) {
        this.setTitle(title);
        this.setValue(initialValue);
        this.setTypeSeance(typeSeance);
    }



    public void Increment() {
        this.setValue(this._value + 1);
        Log.d("test", "value is " + this.getValue());

    }

    public void Decrement() {
        this.setValue(this._value - 1);

        Log.d("test", "value is " + this.getValue());
    }
}
