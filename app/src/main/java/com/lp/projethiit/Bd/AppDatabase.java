package com.lp.projethiit.Bd;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lp.projethiit.Model.Seance;


@Database(entities = {Seance.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //public abstract CategorieDao categorieDao();

}

// pres entités et DAO associé