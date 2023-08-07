package com.example.puzzlescitygame.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.databinding.FragmentChooseCorrectAnswerBinding;
import com.example.puzzlescitygame.databinding.FragmentTrueOrFalseBinding;
import com.example.puzzlescitygame.fragments.fragmentsDilog.Dilog;
import com.example.puzzlescitygame.fragments.fragmentsDilog.truediloganswer;
import com.example.puzzlescitygame.roomdatabase.entity.Question;

import java.util.List;
import java.util.Locale;


public class FragmentTrueOrFalse extends Fragment {
    private static final String ARG_ID = "id";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_TRUE_ANSWER = "true_answer";
    private static final String ARG_POINT = "point";
    private static final String ARG_HINT = "hint";

    private int id;
    private String title;
    private String true_answer;
    private String hint;
    private int point;
    private int timer;

    public FragmentTrueOrFalse() {
    }

    public static FragmentTrueOrFalse newInstance(int id, String title, int timer, int point, String hint, String true_answer) {
        FragmentTrueOrFalse fragment = new FragmentTrueOrFalse();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_PARAM1, title);
        args.putInt(ARG_PARAM2, timer);
        args.putInt(ARG_ID, id);
        args.putString(ARG_TRUE_ANSWER, true_answer);
        args.putInt(ARG_POINT, point);
        args.putString(ARG_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            timer = getArguments().getInt(ARG_PARAM2);
            id = getArguments().getInt(ARG_ID);
            point = getArguments().getInt(ARG_POINT);
            hint = getArguments().getString(ARG_HINT);
            true_answer = getArguments().getString(ARG_TRUE_ANSWER);
        }
    }

    CountDownTimer countDownTimer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTrueOrFalseBinding binding = FragmentTrueOrFalseBinding.inflate(inflater,container,false);
        binding.trueorfalseNameQuestion.setText(title);
        binding.timerView2.setText(String.valueOf(timer));

        String Yes = binding.btnYes.getText().toString();
        String No = binding.btnNo.getText().toString();

        timer *= 20;
        countDownTimer = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long min = (millisUntilFinished / 60000);
                long sec = (millisUntilFinished / 1000) % 60;
                final String timer = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
                binding.timerView2.setText(timer);
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                /*Dilog dialogFragment = Dilog.newInstance("1");
                dialogFragment.show(getParentFragmentManager(),null);
*/
            }
        }.start();

        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Yes.equalsIgnoreCase(true_answer)){
                    Toast.makeText(getContext(), "صح", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    truediloganswer dialogFragment = truediloganswer.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);

                }else {
                    Toast.makeText(getContext(), "خطاء", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    Dilog dialogFragment = Dilog.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);

                }
            }
        });
        
        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (No.equalsIgnoreCase(true_answer)){
                    Toast.makeText(getContext(), "صح", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    truediloganswer dialogFragment = truediloganswer.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);

                }else {
                    Toast.makeText(getContext(), "خطاء", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    Dilog dialogFragment = Dilog.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);
                }
            }
        });
        return binding.getRoot();
    }

}