package com.example.puzzlescitygame.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.puzzlescitygame.fragments.fragmentsDilog.Dilog;
import com.example.puzzlescitygame.databinding.FragmentChooseCorrectAnswerBinding;
import com.example.puzzlescitygame.fragments.fragmentsDilog.truediloganswer;
import com.example.puzzlescitygame.roomdatabase.entity.Question;

import java.util.Locale;

public class FragmentChooseCorrectAnswer extends Fragment {

    private static final String ARG_ID = "id";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_TRUE_ANSWER = "true_answer";
    private static final String ARG_POINT = "point";
    private static final String ARG_Hint = "hint";

    private CountDownTimer countDownTimer;

    private String title;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String true_answer;
    private String hint;
    private int point;
    private int timer;

    public FragmentChooseCorrectAnswer() {
    }

    public static FragmentChooseCorrectAnswer newInstance(String title,String answer1, String answer2, String answer3, String answer4, String true_answer, String hint,int point, int timer) {
        FragmentChooseCorrectAnswer fragment = new FragmentChooseCorrectAnswer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2,answer1);
        args.putString(ARG_PARAM3,answer2);
        args.putString(ARG_PARAM4,answer3);
        args.putString(ARG_PARAM5,answer4);
        args.putString(ARG_PARAM1,title);
        args.putString(ARG_TRUE_ANSWER,true_answer);
        args.putString(ARG_Hint,hint);
        args.putInt(ARG_PARAM6,timer);
        args.putInt(ARG_POINT,point);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            answer1 = getArguments().getString(ARG_PARAM2);
            answer2 = getArguments().getString(ARG_PARAM3);
            answer3 = getArguments().getString(ARG_PARAM4);
            answer4 = getArguments().getString(ARG_PARAM5);
            true_answer = getArguments().getString(ARG_TRUE_ANSWER);
            hint = getArguments().getString(ARG_Hint);
            title = getArguments().getString(ARG_PARAM1);
            timer = getArguments().getInt(ARG_PARAM6);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentChooseCorrectAnswerBinding binding = FragmentChooseCorrectAnswerBinding.inflate(inflater,container,false);
        binding.choosecorrectanswerNameQuestion.setText(title);
        binding.answer1.setText(answer1);
        binding.answer2.setText(answer2);
        binding.answer3.setText(answer3);
        binding.answer4.setText(answer4);

        String Answer1 = binding.answer1.getText().toString();
        String Answer2 = binding.answer2.getText().toString();
        String Answer3 = binding.answer3.getText().toString();
        String Answer4 = binding.answer4.getText().toString();

        timer *= 10;
        countDownTimer = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long min = (millisUntilFinished / 60000);
                long sec = (millisUntilFinished / 1000) % 60;
                final String timer = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
                binding.timerView.setText(timer);
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();

                /*Dilog dialogFragment = Dilog.newInstance("1");
                dialogFragment.show(getParentFragmentManager(),null);
 */
            }
        }.start();

        binding.answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Answer", "onClick: "+Answer1 +"/"+true_answer);
                if (Answer1.equalsIgnoreCase(true_answer)){
                    Toast.makeText(getContext(), "صح", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    truediloganswer trueDialogFragment = truediloganswer.newInstance("1");
                    trueDialogFragment.show(getParentFragmentManager(),null);

                }else {
                    Toast.makeText(getContext(), "خطاء", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    Dilog dialogFragment = Dilog.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);

                }
            }
        });

        binding.answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer2.equalsIgnoreCase(true_answer)){
                    Toast.makeText(getContext(), "صح", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                   truediloganswer trueDialogFragment = truediloganswer.newInstance("1");
                    trueDialogFragment.show(getParentFragmentManager(),null);

                }else {
                    Toast.makeText(getContext(), "خطاء", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    Dilog dialogFragment = Dilog.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);

                }
            }
        });

        binding.answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer3.equalsIgnoreCase(true_answer)){
                    Toast.makeText(getContext(), "صح", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    truediloganswer trueDialogFragment = truediloganswer.newInstance("1");
                    trueDialogFragment.show(getParentFragmentManager(),null);

                }else {
                    Toast.makeText(getContext(), "خطاء", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    Dilog dialogFragment = Dilog.newInstance("1");
                    dialogFragment.show(getParentFragmentManager(),null);

                }
            }
        });

        binding.answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer4.equalsIgnoreCase(true_answer)){
                    Toast.makeText(getContext(), "صح", Toast.LENGTH_SHORT).show();
                    countDownTimer.cancel();
                    truediloganswer trueDialogFragment = truediloganswer.newInstance("1");
                    trueDialogFragment.show(getParentFragmentManager(),null);
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