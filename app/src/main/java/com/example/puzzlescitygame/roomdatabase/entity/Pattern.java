package com.example.puzzlescitygame.roomdatabase.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pattern {
    @PrimaryKey(autoGenerate = true)
    private int idPuzzlePatterns;
    @NonNull
    private String namePuzzlePatterns;

    public Pattern() {
    }

    public Pattern(int idPuzzlePatterns, @NonNull String namePuzzlePatterns) {
        this.idPuzzlePatterns = idPuzzlePatterns;
        this.namePuzzlePatterns = namePuzzlePatterns;
    }

    public int getIdPuzzlePatterns() {
        return idPuzzlePatterns;
    }

    public void setIdPuzzlePatterns(int idPuzzlePatterns) {
        this.idPuzzlePatterns = idPuzzlePatterns;
    }

    @NonNull
    public String getNamePuzzlePatterns() {
        return namePuzzlePatterns;
    }

    public void setNamePuzzlePatterns(@NonNull String namePuzzlePatterns) {
        this.namePuzzlePatterns = namePuzzlePatterns;
    }
}
