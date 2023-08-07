package com.example.puzzlescitygame.sherdPreferance;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.puzzlescitygame.controllers.AppControllers;


public class Sherdpreferanses {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
  //  Resources resources;
  //  private boolean isNightModeEnabled = false;


    private static Sherdpreferanses instance;

    public static Sherdpreferanses getInstance() {
        if (instance == null) {
            instance = new Sherdpreferanses();
        }
        return instance;
    }
/*
    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }*/

    private Sherdpreferanses() {
        sharedPreferences = AppControllers.getInstance().getSharedPreferences("mode", Context.MODE_PRIVATE);
    }

    public boolean isFirstTime_User() {
        boolean ranBefore = sharedPreferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    public boolean isFirstTime_Level() {
        boolean ranBefore = sharedPreferences.getBoolean("level", false);
        if (!ranBefore) {
            editor = sharedPreferences.edit();
            editor.putBoolean("level", true);
            editor.commit();
        }
        return !ranBefore;
    }

    public int getScore() {
        boolean ranBefore = sharedPreferences.getBoolean("score", false);
        if (!ranBefore) {
            editor = sharedPreferences.edit();
            editor.putBoolean("score", true);
            editor.commit();
        }
        return 0;
    }

}
