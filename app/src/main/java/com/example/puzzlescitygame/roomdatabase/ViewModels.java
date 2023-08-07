package com.example.puzzlescitygame.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.puzzlescitygame.roomdatabase.entity.Level;
import com.example.puzzlescitygame.roomdatabase.entity.Pattern;
import com.example.puzzlescitygame.roomdatabase.entity.Question;
import com.example.puzzlescitygame.roomdatabase.entity.Statistics;
import com.example.puzzlescitygame.roomdatabase.entity.User;

import java.util.List;

public class ViewModels extends AndroidViewModel {
    private Repository repository;

    public ViewModels(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertUser(User user){
        repository.insertUser(user);
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    public void updateUser(User user){
        repository.updateUser(user);
    }

    public LiveData<List<User>> getAllUser(){
        return repository.getAllUser();
    }
    // -------------------------------------------------------------------------------------------------
    public void insertLevel(Level level){
        repository.insertLevel(level);
    }

    public void  deleteLevel(Level level) {
        repository.deleteLevel(level);
    }

    public void  updateLevel(Level level){
        repository.updateLevel(level);
    }

    public LiveData<List<Level>> getAllLevel(){
        return repository.getAllLevel();
    }
    // -------------------------------------------------------------------------------------------------
    public void insertQuestion(Question question){
        repository.insertQuestion(question);
    }

    public void  deleteQuestion(Question question) {
        repository.deleteQuestion(question);
    }

    public void  updateQuestion(Question question){
        repository.updateQuestion(question);
    }

    public LiveData<List<Question>> getAllQuestion(int id){
        return repository.getAllQuestion(id);
    }

    public LiveData<List<Question>> getAllQuestionById(int idLevelFK){
        return repository.getAllQuestionById(idLevelFK);
    }
    // -------------------------------------------------------------------------------------------------
    public void insertPattern(Pattern pattern){
        repository.insertPattern(pattern);
    }

    public void deletePattern(Pattern pattern) {
        repository.deletePattern(pattern);
    }

    public void updatePattern(Pattern pattern){
        repository.updatePattern(pattern);
    }

    public LiveData<List<Pattern>> getAllPattern(){
        return repository.getAllPattern();
    }

    // -------------------------------------------------------------------------------------------------
    public LiveData<List<Statistics>> getStatistics() {
        return repository.getStatistics();
    }
    public LiveData<List<Statistics>> getStatisticsLevelNo(int levelNo) {
        return repository.getStatisticsLevelNo(levelNo);
    }
    public void insertStatistics(Statistics statistics){
        repository.insertStatistics(statistics);
    }

}