package com.example.puzzlescitygame.roomdatabase.entityDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.puzzlescitygame.roomdatabase.entity.Level;

import java.util.List;

@Dao
public interface Level_DAO {
    @Insert
    long insertLevel(Level level);
    @Update
    int updateLevel(Level level);
    @Delete
    int deleteLevel(Level level);

    @Query("select * from Level")
    LiveData<List<Level>> getAllLevel();
}
