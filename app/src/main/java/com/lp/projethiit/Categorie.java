package com.lp.projethiit;

import android.util.Log;

import java.io.Serializable;

public class Categorie implements Serializable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Categorie(String title, int initialValue) {
        this.setTitle(title);
        this.setValue(initialValue);
    }

    public void increment() {
        this.value++;
        Log.d("test", "valuue is " + this.getValue());

    }

    public void decrement() {
        this.value--;
        Log.d("test", "valuue is " + this.getValue());
    }
}
