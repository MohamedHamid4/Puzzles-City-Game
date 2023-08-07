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
import com.example.puzzlescitygame.databinding.FragmentDilogBinding;
import com.example.puzzlescitygame.interfeses.OnClickListener2;


public class truediloganswer extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public truediloganswer() {
    }

    OnClickListener2 listener2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickListener2){
            listener2 = (OnClickListener2) context;
        }
    }

    public static truediloganswer newInstance(String param1) {
        truediloganswer fragment = new truediloganswer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        FragmentDilogBinding binding = FragmentDilogBinding.inflate(getLayoutInflater());

        MediaPlayer mediaPlayer3 = MediaPlayer.create(getContext(), R.raw.music_correct);
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