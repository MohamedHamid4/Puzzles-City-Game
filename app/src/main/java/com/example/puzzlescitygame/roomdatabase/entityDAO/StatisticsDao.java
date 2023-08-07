package com.example.puzzlescitygame.roomdatabase.entityDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlescitygame.roomdatabase.entity.Statistics;

import java.util.List;

@Dao
public interface StatisticsDao {
    @Insert
    long insertStatistics(Statistics statistics);

    @Query("SELECT * FROM Statistics order by id asc")
    LiveData<List<Statistics>> getStatistics();

    @Query("SELECT * FROM Statistics where levelNo =:levelNo order by id asc")
    LiveData<List<Statistics>> getStatisticsLevelNo(int levelNo);


}
