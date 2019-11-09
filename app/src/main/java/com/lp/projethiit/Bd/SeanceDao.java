package com.lp.projethiit.Bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SeanceDao {

    @Query("SELECT * FROM seances")
    List<Seance> getAll();

    @Insert
    void insert(Seance seance);

    @Delete
    void delete(Seance seance);

    @Update
    void update(Seance seance);

    // cours de vendredi select *.... = id

    // new activity avec inflacte

}