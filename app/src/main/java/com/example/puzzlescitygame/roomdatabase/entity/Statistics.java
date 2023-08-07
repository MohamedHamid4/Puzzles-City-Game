package com.example.puzzlescitygame.roomdatabase.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys ={@ForeignKey(entity = Level.class,parentColumns = {"idLevel"},
        childColumns = {"levelNo"},onDelete = CASCADE,onUpdate = CASCADE)})
public class Statistics {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int point;
    private int win;
    private int loss;
    private int levelNo;

    public Statistics(int point, int win, int loss, int levelNo) {
        this.point = point;
        this.win = win;
        this.loss = loss;
        this.levelNo = levelNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        loss = loss;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }
}
