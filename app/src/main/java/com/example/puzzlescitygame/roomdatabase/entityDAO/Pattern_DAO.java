package com.example.puzzlescitygame.roomdatabase.entityDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.puzzlescitygame.roomdatabase.entity.Pattern;

import java.util.List;

@Dao
public interface Pattern_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertPattern(Pattern pattern);
    @Update
    int updatePattern(Pattern  pattern);
    @Delete
    int deletePattern(Pattern pattern);

    @Query("select * from Pattern")
    LiveData<List<Pattern>> getAllPattern();
}
