package com.example.puzzlescitygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.databinding.ActivitySplashScreenBinding;
import com.example.puzzlescitygame.roomdatabase.ViewModels;
import com.example.puzzlescitygame.roomdatabase.entity.Level;
import com.example.puzzlescitygame.roomdatabase.entity.Pattern;
import com.example.puzzlescitygame.roomdatabase.entity.Question;
import com.example.puzzlescitygame.sherdPreferance.Sherdpreferanses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Splash_Screen extends AppCompatActivity {
ActivitySplashScreenBinding activitySplashScreenBinding;
private int count = 0;
private JSONObject object, object2;
ViewModels viewModel;
private Pattern pattern;
private Question questions;
private Level level;
private JSONArray jr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(activitySplashScreenBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(ViewModels.class);

        // هاد الكود علشان اذا البيانات موجودة ما يرجع يحمله مرة تانية بالصفحة
        if (Sherdpreferanses.getInstance().isFirstTime_Level()){
            getAllData();
            getAllData2();
        }


        Animation logoAnimation = AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.zoom_animation);
        Animation nameAnimation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.zoom_animation);

        Animation topView1Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.top_views_animation);
        Animation topView2Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.top_views_animation);
        Animation topView3Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.top_views_animation);

        Animation bottomView1Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.bottom_views_animation);
        Animation bottomView2Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.bottom_views_animation);
        Animation bottomView3Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.bottom_views_animation);

        activitySplashScreenBinding.topView1.startAnimation(topView1Animation);
        activitySplashScreenBinding.bottomView1.startAnimation(bottomView1Animation);

        activitySplashScreenBinding.topView1.setVisibility(View.VISIBLE);
        activitySplashScreenBinding.bottomView1.setVisibility(View.VISIBLE);

        topView1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activitySplashScreenBinding.topView2.setVisibility(View.VISIBLE);
                activitySplashScreenBinding.bottomView2.setVisibility(View.VISIBLE);

                activitySplashScreenBinding.topView2.startAnimation(topView2Animation);
                activitySplashScreenBinding.bottomView2.startAnimation(bottomView2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topView2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activitySplashScreenBinding.topView3.setVisibility(View.VISIBLE);
                activitySplashScreenBinding.bottomView3.setVisibility(View.VISIBLE);

                activitySplashScreenBinding.topView3.startAnimation(topView3Animation);
                activitySplashScreenBinding.bottomView3.startAnimation(bottomView3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topView3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activitySplashScreenBinding.logo.setVisibility(View.VISIBLE);
                activitySplashScreenBinding.logo.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activitySplashScreenBinding.welcomeText.setVisibility(View.VISIBLE);
                activitySplashScreenBinding.welcomeText.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
// تابع كود الانيميشن
    @Override
    protected void onStart() {
        super.onStart();
        handelSplashActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void handelSplashActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(intent);
            }
        },4000);
    }

    private void getAllData() {
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                int level_no = object.getInt("level_no");
                Log.e("TAG", "getAllData: level_no " + level_no);
                int unlock_points = object.getInt("unlock_points");
                Log.e("TAG", "getAllData: unlock_points " + unlock_points);
                level = new Level(level_no, unlock_points);
                viewModel.insertLevel(level);

                jr = object.getJSONArray("questions");

                for (int j = 0; j < jr.length(); j++) {
                    object = jr.getJSONObject(j);
                    object2 = object.getJSONObject("pattern");
                    int pattern_id = object2.getInt("pattern_id");
                    String pattern_name = object2.getString("pattern_name");
                    pattern = new Pattern(pattern_id, pattern_name);
                    viewModel.insertPattern(pattern);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", "getAllData: error");
        }
    }
    private void getAllData2() {
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                int level_no = object.getInt("level_no");
                Log.e("TAG", "getAllData: level_no " + level_no);
                int unlock_points = object.getInt("unlock_points");
                Log.e("TAG", "getAllData: unlock_points " + unlock_points);

                jr = object.getJSONArray("questions");

                for (int j = 0; j < jr.length(); j++) {
                    object = jr.getJSONObject(j);
                    String title = object.getString("title");
                    String answer_1 = object.getString("answer_1");
                    String answer_2 = object.getString("answer_2");
                    String answer_3 = object.getString("answer_3");
                    String answer_4 = object.getString("answer_4");
                    String true_answer = object.getString("true_answer");
                    int points = object.getInt("points");
                    int duration = object.getInt("duration");
                    String hint = object.getString("hint");

                    object2 = object.getJSONObject("pattern");

                    int pattern_id = object2.getInt("pattern_id");

                    questions = new Question(title, answer_1, answer_2, answer_3, answer_4, true_answer, points, level_no, duration, pattern_id , hint);
                    viewModel.insertQuestion(questions);
                    Log.e("TAG", "getAllData: " + title);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", "getAllData: error");
        }
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getBaseContext().getAssets().open("puzzleGameData.json"); //تقوم بجلب ملف الجيسن
            int size = is.available(); //بتجبلك البايتات فيه كم حجمها
            byte[] buffer = new byte[size];
            //Stream : assets  فتح قناه ما بين الكلاس الخاص فيا وما بين ملف ال
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8); // convert byte to String
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

}