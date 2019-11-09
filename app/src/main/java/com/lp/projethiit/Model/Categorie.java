package com.lp.projethiit.Model;

import android.util.Log;

import java.io.Serializable;

// ici on est d ns un modele

// la donnée qui va remplir la ligne

public class Categorie implements Serializable {

    private int id;

    private String _title;
    private int _value;

    public Categorie() {

    }

    public Categorie(String title, int initialValue) {
        this.setTitle(title);
        this.setValue(initialValue);
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


    private void setValue(int value) {
        if (value >= 0){
            this._value = value;
        }
    }

    public void Increment() {
        this.setValue(this._value + 1);
        Log.d("test", "value is " + this.getValue());

    }

    public void Decrement() {
        this.setValue(this._value - 1);
        Log.d("test", "value is " + this.getValue());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_value(int _value) {
        this._value = _value;
    }
}
