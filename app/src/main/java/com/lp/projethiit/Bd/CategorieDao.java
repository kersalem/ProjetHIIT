package com.lp.projethiit.Bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategorieDao {

    @Query("SELECT * FROM categorie")
    List<Categorie> getAll();

    @Insert
    void insert(Categorie categorie);

    @Insert
    long[] insertAll(Categorie... categories);

    @Delete
    void delete(Categorie categorie);

    @Update
    void update(Categorie categorie);

}