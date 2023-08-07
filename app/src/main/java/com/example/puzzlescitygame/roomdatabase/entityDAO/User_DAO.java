package com.example.puzzlescitygame.roomdatabase.entityDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.puzzlescitygame.roomdatabase.entity.User;

import java.util.List;

@Dao
public interface User_DAO {
    @Insert
    long insertUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    int deleteUser(User user);

    @Query("select * from User")
    LiveData<List<User>> getAllUser();
}
