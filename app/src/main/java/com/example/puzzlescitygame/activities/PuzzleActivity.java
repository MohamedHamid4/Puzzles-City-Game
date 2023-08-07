package com.example.puzzlescitygame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.adapter.AdapterViewPager;
import com.example.puzzlescitygame.databinding.ActivityPuzzleBinding;
import com.example.puzzlescitygame.fragments.FragmentChooseCorrectAnswer;
import com.example.puzzlescitygame.fragments.FragmentFillAnswer;
import com.example.puzzlescitygame.fragments.FragmentTrueOrFalse;
import com.example.puzzlescitygame.fragments.fragmentsDilog.Dilog;
import com.example.puzzlescitygame.interfeses.OnClickListener2;
import com.example.puzzlescitygame.roomdatabase.ViewModels;
import com.example.puzzlescitygame.roomdatabase.entity.Question;

import java.util.ArrayList;
import java.util.List;


public class PuzzleActivity extends AppCompatActivity implements OnClickListener2 {
    ActivityPuzzleBinding binding;
    ArrayList<Fragment> list = new ArrayList<>();
    ViewModels viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(ViewModels.class);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id_level", 0);

        viewModel.getAllQuestion(id).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                for (int i = 0; i < questions.size(); i++) {
                    if (questions.get(i).getQuestionPattern() == 1) {
                        list.add(FragmentTrueOrFalse.newInstance(questions.get(i).getIdPuzzle(), questions.get(i).getTextPuzzle(),questions.get(i).getDurationSeconds(),questions.get(i).getNumberPoints(),questions.get(i).getInstructionalText(),questions.get(i).getCorrectAnswer()));
                    } else if (questions.get(i).getQuestionPattern() == 2) {
                        list.add(FragmentChooseCorrectAnswer.newInstance(questions.get(i).getTextPuzzle(), questions.get(i).getAnswerOne(),
                                questions.get(i).getAnswerTwo(), questions.get(i).getAnswerThree(), questions.get(i).getAnswerFour(),questions.get(i).getCorrectAnswer(),questions.get(i).getInstructionalText(),questions.get(i).getNumberPoints(),questions.get(i).getDurationSeconds()));
                    } else if (questions.get(i).getQuestionPattern() == 3) {
                        list.add(FragmentFillAnswer.newInstance(questions.get(i).getIdPuzzle(),questions.get(i).getTextPuzzle(),questions.get(i).getCorrectAnswer(),questions.get(i).getInstructionalText(),questions.get(i).getNumberPoints(),questions.get(i).getDurationSeconds()));
                    }
                    Question question1 = questions.get(i);
                    binding.avgquestion.setText(String.valueOf(question1.getIdPuzzle()));
                }
                Question question1 = questions.get(0);
                binding.idlevel.setText(String.valueOf(question1.getIdLevelFK()));

                AdapterViewPager adapterViewPager = new AdapterViewPager(PuzzleActivity.this, list);
                binding.viewPager2.setAdapter(adapterViewPager);
                binding.viewPager2.setUserInputEnabled(false);
            }
        });

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.viewPager2.setCurrentItem(getItem(+1), true);
            }
        });
    }

//       هذه الدالة كنت مستخدمها لاخر ديلوج رج يطهر للمستخدم في اخر سؤال من الليفل
/*    Dilog fragmentDilog;
    private void moveViewPager() {
        int currentItem = binding.viewPager2.getCurrentItem();
        if (currentItem == AdapterViewPager.getItemCount() - 1) {
            fragmentDilog = Dilog.newInstance(getResources().getString(R.string.end_level));
            fragmentDilog.show(getSupportFragmentManager(), "dialog");
            fragmentDilog.setCancelable(false);
        } else {
            binding.viewPager2.setCurrentItem(currentItem + 1, false);
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PuzzleActivity.this, StartPlaying_Activity.class));
    }

    private int getItem(int i) {
        return binding.viewPager2.getCurrentItem() + i;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void sOnClickListener() {
        binding.viewPager2.setCurrentItem(getItem(+1), true);
    }
}