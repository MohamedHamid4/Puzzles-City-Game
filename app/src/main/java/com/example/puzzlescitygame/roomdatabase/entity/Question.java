package com.example.puzzlescitygame.roomdatabase.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys ={@ForeignKey(entity = Level.class,parentColumns = {"idLevel"},
        childColumns = {"idLevelFK"},onDelete = CASCADE,onUpdate = CASCADE),
        @ForeignKey(entity = Pattern.class,parentColumns = {"idPuzzlePatterns"},
        childColumns = {"QuestionPattern"},onDelete = CASCADE,onUpdate = CASCADE)})
public class Question implements Serializable {
        @PrimaryKey(autoGenerate = true)
        public int idPuzzle;
        @NonNull
        public String textPuzzle;
        @NonNull
        public String answerOne;
        @NonNull
        public String answerTwo;
        @NonNull
        public String answerThree;
        @NonNull
        public String answerFour;
        @NonNull
        public String correctAnswer;
        @NonNull
        private int numberPoints;
        @NonNull
        public int idLevelFK;
        @NonNull
        public int DurationSeconds;
        @NonNull
        public int QuestionPattern;
        @NonNull
        public String InstructionalText;

    public Question() {
    }

    public Question(@NonNull String textPuzzle, @NonNull String answerOne, @NonNull String answerTwo, @NonNull String answerThree, @NonNull String answerFour, @NonNull String correctAnswer, int numberPoints, int idLevelFK, int durationSeconds, int questionPattern, @NonNull String instructionalText) {
        this.textPuzzle = textPuzzle;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.correctAnswer = correctAnswer;
        this.numberPoints = numberPoints;
        this.idLevelFK = idLevelFK;
        DurationSeconds = durationSeconds;
        QuestionPattern = questionPattern;
        InstructionalText = instructionalText;
    }

    public int getIdPuzzle() {
        return idPuzzle;
    }

    public void setIdPuzzle(int idPuzzle) {
        this.idPuzzle = idPuzzle;
    }

    @NonNull
    public String getTextPuzzle() {
        return textPuzzle;
    }

    public void setTextPuzzle(@NonNull String textPuzzle) {
        this.textPuzzle = textPuzzle;
    }

    @NonNull
    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(@NonNull String answerOne) {
        this.answerOne = answerOne;
    }

    @NonNull
    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(@NonNull String answerTwo) {
        this.answerTwo = answerTwo;
    }

    @NonNull
    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(@NonNull String answerThree) {
        this.answerThree = answerThree;
    }

    @NonNull
    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(@NonNull String answerFour) {
        this.answerFour = answerFour;
    }

    @NonNull
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(@NonNull String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getNumberPoints() {
        return numberPoints;
    }

    public void setNumberPoints(int numberPoints) {
        this.numberPoints = numberPoints;
    }

    public int getIdLevelFK() {
        return idLevelFK;
    }

    public void setIdLevelFK(int idLevelFK) {
        this.idLevelFK = idLevelFK;
    }

    public int getDurationSeconds() {
        return DurationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        DurationSeconds = durationSeconds;
    }

    public int getQuestionPattern() {
        return QuestionPattern;
    }

    public void setQuestionPattern(int questionPattern) {
        QuestionPattern = questionPattern;
    }

    @NonNull
    public String getInstructionalText() {
        return InstructionalText;
    }

    public void setInstructionalText(@NonNull String instructionalText) {
        InstructionalText = instructionalText;
    }
}
