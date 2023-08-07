package com.example.puzzlescitygame.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.puzzlescitygame.roomdatabase.entity.Level;
import com.example.puzzlescitygame.roomdatabase.entity.Pattern;
import com.example.puzzlescitygame.roomdatabase.entity.Question;
import com.example.puzzlescitygame.roomdatabase.entity.Statistics;
import com.example.puzzlescitygame.roomdatabase.entity.User;
import com.example.puzzlescitygame.roomdatabase.entityDAO.Level_DAO;
import com.example.puzzlescitygame.roomdatabase.entityDAO.Pattern_DAO;
import com.example.puzzlescitygame.roomdatabase.entityDAO.Question_DAO;
import com.example.puzzlescitygame.roomdatabase.entityDAO.StatisticsDao;
import com.example.puzzlescitygame.roomdatabase.entityDAO.User_DAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    @Database(entities = {User.class, Level.class, Question.class, Pattern.class, Statistics.class}, version = 1, exportSchema = false)
    public abstract class DatabasePuzzles extends RoomDatabase {
        public abstract User_DAO userDao();
        public abstract Level_DAO levelDao();
        public abstract Question_DAO questionDao();
        public abstract Pattern_DAO patternDao();
        public abstract StatisticsDao statisticsDao();

        private static volatile DatabasePuzzles INSTANCE;
        private static final int NUMBER_OF_THREADS = 6;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static DatabasePuzzles getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (DatabasePuzzles.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                DatabasePuzzles.class, "database_puzzles").build();
                    }
                }
            }
            return INSTANCE;
        }
    }