package com.example.puzzlescitygame.fragments.fragmentsDilog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.View;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.activities.PuzzleActivity;
import com.example.puzzlescitygame.adapter.AdapterViewPager;
import com.example.puzzlescitygame.databinding.FragmentDilogBinding;
import com.example.puzzlescitygame.interfeses.OnClickListener2;

public class Dilog extends DialogFragment {

    private static final String ARG_HINT = "hint";

    private String hint;

    public Dilog() {
    }

    OnClickListener2 listener2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickListener2){
            listener2 = (OnClickListener2) context;
        }
    }

    public static Dilog newInstance(String hint) {
        Dilog fragment = new Dilog();
        Bundle args = new Bundle();
        args.putString(ARG_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hint = getArguments().getString(ARG_HINT);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        FragmentDilogBinding binding = FragmentDilogBinding.inflate(getLayoutInflater());

        MediaPlayer mediaPlayer3 = MediaPlayer.create(getContext(), R.raw.music_incorrect);
        mediaPlayer3.start();

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener2.sOnClickListener();
                dismiss();
            }
        });

        builder.setView(binding.getRoot());
        return builder.create();
    }
}