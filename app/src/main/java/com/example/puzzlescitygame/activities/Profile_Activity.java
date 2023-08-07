package com.example.puzzlescitygame.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.databinding.ActivityProfileBinding;
import com.example.puzzlescitygame.roomdatabase.ViewModels;
import com.example.puzzlescitygame.roomdatabase.entity.User;
import com.example.puzzlescitygame.sherdPreferance.Sherdpreferanses;

import java.util.ArrayList;
import java.util.List;

public class Profile_Activity extends AppCompatActivity {
    ActivityProfileBinding activityProfileBinding;
    ViewModels viewModel;
    User userEntity;
    private ArrayList<User> list = new ArrayList<>();
    int id;
    MediaPlayer mediaPlayer;

    private void startSound(){
        mediaPlayer = MediaPlayer.create(Profile_Activity.this, R.raw.clicked);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(activityProfileBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(ViewModels.class);

        if (Sherdpreferanses.getInstance().isFirstTime_User()){
            userEntity = new User("player1","player@gmail.com","12/7/2002","male","palestine");
            id = userEntity.getIdUser();
            viewModel.insertUser(userEntity);
        }

        GET_USER();

        activityProfileBinding.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                Intent intent = new Intent(Profile_Activity.this, Edit_Profile.class);
                intent.putExtra("UserId",userEntity.getIdUser());
                intent.putExtra("UserName",userEntity.getNameUser());
                intent.putExtra("UserEmail",userEntity.getEmailUser());
                intent.putExtra("UserBirthdate",userEntity.getBirthdateUser());
                intent.putExtra("UserCountry",userEntity.getCountryUser());
                intent.putExtra("UserGender",userEntity.getGenderUser());
                startActivity(intent);
            }
        });

    }
            private void GET_USER() {
            viewModel = new ViewModelProvider(this).get(ViewModels.class);
            viewModel.getAllUser().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    userEntity = users.get(0);
                    id = userEntity.getIdUser();
                    activityProfileBinding.tvUsername.setText(userEntity.getNameUser());
                    activityProfileBinding.tvEmail.setText(userEntity.getEmailUser());
                    activityProfileBinding.tvBirthdate.setText(userEntity.getBirthdateUser());
                    activityProfileBinding.tvCountry.setText(userEntity.getCountryUser());
                    activityProfileBinding.tvRadioGroup.setText(userEntity.getGenderUser());
                }
            });
        }

}
