package com.lp.projethiit.Bd;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// ici on est d ns un modele

// la donnée qui va remplir la ligne
@Entity(tableName = "categorie")
public class Categorie implements Serializable {

    @PrimaryKey(autoGenerate = true)
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

    public int getValueEnMiliseconds() {
        return _value*1000;
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
