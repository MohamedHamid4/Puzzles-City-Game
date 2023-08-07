package com.example.puzzlescitygame.controllers;

import android.app.Application;

public class AppControllers extends Application {
    private static AppControllers instance;

    public static AppControllers getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
