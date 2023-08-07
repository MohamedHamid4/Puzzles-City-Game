package com.example.puzzlescitygame.activities;

import android.app.Dialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.databinding.ActivitySettingsBinding;
import com.example.puzzlescitygame.service.MyJobService;
import com.example.puzzlescitygame.service.musicService;

import java.util.concurrent.TimeUnit;

public class Settings_Activity extends AppCompatActivity {
ActivitySettingsBinding activitySettingsBinding;
    Dialog dilog ;
    MediaPlayer mediaPlayer;

   private void startSound(){
       mediaPlayer = MediaPlayer.create(Settings_Activity.this, R.raw.clicked);
       mediaPlayer.start();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        dilog = new Dialog(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        activitySettingsBinding.switchMusic.setChecked(sharedPreferences.getBoolean("Music",false));

        activitySettingsBinding.switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b){
                    Intent intent = new Intent(getApplicationContext(), musicService.class);
                    startService(intent);
                    editor.putBoolean("Music",true);
                    editor.apply();
                } else {
                    Intent intent = new Intent(getApplicationContext(),musicService.class);
                    stopService(intent);
                    editor.putBoolean("Music",false);
                    editor.apply();
                }
            }
        });

        activitySettingsBinding.switchSounds.setChecked(sharedPreferences.getBoolean("sound",false));

        activitySettingsBinding.switchSounds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor.putBoolean("sound",true);
                    editor.apply();
                } else {
                    editor.putBoolean("sound",false);
                    editor.apply();
                }
            }
        });

        activitySettingsBinding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                Intent intent = new Intent(Settings_Activity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });


        activitySettingsBinding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                openResetDilog();

            }
        });
        activitySettingsBinding.switchNotifications.setChecked(sharedPreferences.getBoolean("Notifications",false));

        activitySettingsBinding.switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("Notifications",b);
                editor.apply();
                ComponentName componentName =new ComponentName(getBaseContext(), MyJobService.class);
                JobInfo jobInfo;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                    jobInfo = new JobInfo.Builder(10,componentName).
                            setPeriodic(TimeUnit.DAYS.toMillis(1))
                            .build();
                }
                else {
                    jobInfo = new JobInfo.Builder(10,componentName).
                            setMinimumLatency(TimeUnit.DAYS.toMillis(1))
                            .build();
                }
                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.schedule(jobInfo);
            }
        });

    }

    //Custom Reset Dilog
    private void openResetDilog() {
        dilog.setContentView(R.layout.custom_dilog_reset);
        dilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn_yes = dilog.findViewById(R.id.btn_yes_reset);
        Button btn_cancel = dilog.findViewById(R.id.btn_no_reset);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View b = getLayoutInflater().inflate(R.layout.custom_no_reset, null);
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
                View w = getLayoutInflater().inflate(R.layout.custom_yes_reset, null);
                Toast toast = new Toast(getBaseContext());
                toast.setView(w);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
                dilog.dismiss();
            }
        });
        dilog.show();
    }

}