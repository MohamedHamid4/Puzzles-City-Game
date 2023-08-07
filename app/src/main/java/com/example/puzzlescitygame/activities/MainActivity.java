package com.example.puzzlescitygame.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.databinding.ActivityMainBinding;
import com.example.puzzlescitygame.service.musicService;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    SharedPreferences preferences;
    Dialog dilog ;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        dilog = new Dialog(this);

        activityMainBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                Intent intent = new Intent(MainActivity.this, StartPlaying_Activity.class);
                startActivity(intent);
            }
        });

        activityMainBinding.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                Intent intent = new Intent(MainActivity.this, Settings_Activity.class);
                startActivity(intent);
            }
        });

        activityMainBinding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                openExitDilog();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        startService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }

    private void startService(){
        SharedPreferences preferences = getSharedPreferences("shar",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        boolean aBoolean = preferences.getBoolean("Music",true);
        if (aBoolean){
            Intent intent = new Intent(getApplicationContext(), musicService.class);
            startService(intent);
            editor.putBoolean("Music",true);
            editor.apply();
        }
    }

    private void stopService(){
        SharedPreferences preferences = getSharedPreferences("shar",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Intent intent = new Intent(getApplicationContext(),musicService.class);
        stopService(intent);
        editor.putBoolean("Music",false);
        editor.apply();
    }

    // هذا بس لما تدعس على زر يطلع صوت  فعله
        private void startSound(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.clicked);
        mediaPlayer.start();
    }
    //-------------------------------------------------------------------------------------------
    //Custom Exit Dilog
        private void openExitDilog() {
            dilog.setContentView(R.layout.custom_dilog_exit);
            dilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btn_yes = dilog.findViewById(R.id.btn_yes);
            Button btn_cancel = dilog.findViewById(R.id.btn_cancel);

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View b = getLayoutInflater().inflate(R.layout.custom_cancel, null);
                    Toast toast = new Toast(getBaseContext());
                    toast.setView(b);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    dilog.dismiss();
                }
            });

            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View w = getLayoutInflater().inflate(R.layout.custom_yes, null);
                    Toast toast = new Toast(getBaseContext());
                    toast.setView(w);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    dilog.dismiss();
                    finish();
                }
            });
            dilog.show();
        }

}