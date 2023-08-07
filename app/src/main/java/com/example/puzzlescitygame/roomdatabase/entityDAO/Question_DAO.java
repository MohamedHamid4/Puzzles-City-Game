package com.example.puzzlescitygame.roomdatabase.entityDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.puzzlescitygame.roomdatabase.entity.Question;

import java.util.List;

@Dao
public interface Question_DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertQuestion(Question question);
    @Update
    int updateQuestion(Question question);
    @Delete
    int deleteQuestion(Question question);

   /* @Query("select * from Question")
    LiveData<List<Question>> getAllQuestion();
*/
    @Query("select * from Question where idLevelFK =:LevelNo")
    LiveData<List<Question>> getAllPuzzleDataById(int LevelNo);
}
