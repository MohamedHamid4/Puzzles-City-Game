package com.example.puzzlescitygame.roomdatabase.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Level {
    @PrimaryKey
    private int idLevel;
    @NonNull
    private int scoreLevel;

    public Level(int idLevel, int scoreLevel) {
        this.idLevel = idLevel;
        this.scoreLevel = scoreLevel;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public int getScoreLevel() {
        return scoreLevel;
    }

    public void setScoreLevel(int scoreLevel) {
        this.scoreLevel = scoreLevel;
    }
}
