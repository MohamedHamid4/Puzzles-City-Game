package com.example.puzzlescitygame.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.puzzlescitygame.adapter.AdapterLevel;
import com.example.puzzlescitygame.databinding.ActivityStartPlayingBinding;
import com.example.puzzlescitygame.roomdatabase.ViewModels;
import com.example.puzzlescitygame.roomdatabase.entity.Level;


import java.util.ArrayList;
import java.util.List;

public class StartPlaying_Activity extends AppCompatActivity {
ActivityStartPlayingBinding activityStartPlayingBinding;

    ViewModels viewModel;
    AdapterLevel adapter;
    ArrayList<Level> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStartPlayingBinding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(activityStartPlayingBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModels.class);

        viewModel.getAllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levelEntities) {
                list = new ArrayList<>();
                adapter.setList(levelEntities);
                activityStartPlayingBinding.recyclerView.setAdapter(adapter);
                activityStartPlayingBinding.recyclerView.setHasFixedSize(true);
                activityStartPlayingBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
       });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();
    }

    private void ALL_METHOD() {
        SET_ADAPTER();
    }

    private void SET_ADAPTER() {
        adapter = new AdapterLevel(list, getApplicationContext(), new AdapterLevel.CallLevel() {
            @Override
            public void callLevel(int x) {
                Intent intent = new Intent(getApplicationContext(), PuzzleActivity.class);
                intent.putExtra("id_level", x);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}