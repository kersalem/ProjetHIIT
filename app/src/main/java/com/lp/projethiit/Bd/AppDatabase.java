package com.lp.projethiit.Bd;


import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Seance.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SeanceDao SeanceDao();

    //public abstract CategorieDao categorieDao();

}

// pres entités et DAO associé