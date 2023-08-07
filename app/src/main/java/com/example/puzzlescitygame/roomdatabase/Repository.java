package com.example.puzzlescitygame.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

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

import java.util.List;

public class Repository {
    User_DAO userDao;
    Level_DAO levelDao;
    Question_DAO questionDao;
    Pattern_DAO patternDao;
    StatisticsDao statisticsDao;

    public Repository(Application application){
        DatabasePuzzles database = DatabasePuzzles.getDatabase(application);
        userDao = database.userDao();
        levelDao = database.levelDao();
        questionDao = database.questionDao();
        patternDao = database.patternDao();
        statisticsDao = database.statisticsDao();
    }

    public void insertUser(User user){
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });
    }

    void deleteUser(User user){
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser(user);

            }
        });
    }

    void updateUser(User user){
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateUser(user);
            }
        });
    }

    LiveData<List<User>> getAllUser(){
        return userDao.getAllUser();
    }
    // -------------------------------------------------------------------------------------------------
    public void insertLevel(Level level) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.insertLevel(level);
            }
        });
    }

    public void updateLevel(Level level) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.updateLevel(level);
            }
        });
    }

    public void deleteLevel(Level level) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.deleteLevel(level);
            }
        });
    }
    public LiveData<List<Level>>getAllLevel(){
        return levelDao.getAllLevel();
    }
    // -------------------------------------------------------------------------------------------------
    public void insertQuestion(Question question) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.insertQuestion(question);
            }
        });
    }

    public void updateQuestion(Question question) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.updateQuestion(question);
            }
        });
    }

    public void deleteQuestion(Question question) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                questionDao.deleteQuestion(question);
            }
        });
    }
    public LiveData<List<Question>>getAllQuestion(int id){
        return questionDao.getAllPuzzleDataById(id);
    }

    LiveData<List<Question>> getAllQuestionById(int idLevelFK){

        return getAllQuestionById(idLevelFK);
    }
    // -------------------------------------------------------------------------------------------------
    public void insertPattern(Pattern pattern) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                patternDao.insertPattern(pattern);
            }
        });
    }

    public void updatePattern(Pattern pattern) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                patternDao.updatePattern(pattern);
            }
        });
    }

    public void deletePattern(Pattern pattern) {
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                patternDao.deletePattern(pattern);
            }
        });
    }
    public LiveData<List<Pattern>>getAllPattern(){
        return patternDao.getAllPattern();
    }

    // -------------------------------------------------------------------------------------------------
    public void insertStatistics(Statistics statistics){
        DatabasePuzzles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                statisticsDao.insertStatistics(statistics);

            }
        });
    }

    LiveData<List<Statistics>> getStatistics() {
        return statisticsDao.getStatistics();
    }

    LiveData<List<Statistics>> getStatisticsLevelNo(int levelNo) {
        return statisticsDao.getStatisticsLevelNo(levelNo);
    }

}
