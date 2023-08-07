package com.example.puzzlescitygame.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.activities.MainActivity;

public class MyJobService extends JobService {
public static final String Channel_Id = "x_Channel_Id";
    @Override
    public boolean onStartJob(JobParameters params) {
        NotificationChannel();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public void NotificationChannel() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getBoolean("Notifications", false)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(Channel_Id, "Channel Display Name", NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("my channel description");
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent},0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_Id);
            builder.setContentTitle("Play Now").setContentText("It's been A Along time Since I've Seen You Play").
                    setSmallIcon(R.drawable.puzzles).
                    setContentIntent(pendingIntent).
                    setPriority(NotificationCompat.PRIORITY_HIGH).
                    setVibrate(new long[]{2000,4000,6000}).
                    setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.app_name))).
                    addAction(R.drawable.puzzles, getString(R.string.app_name), pendingIntent);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(1, builder.build());
        }
    }

}