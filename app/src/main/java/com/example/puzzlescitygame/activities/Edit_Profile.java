package com.example.puzzlescitygame.activities;

import android.content.Intent;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.puzzlescitygame.R;
import com.example.puzzlescitygame.databinding.ActivityEditProfileBinding;
import com.example.puzzlescitygame.roomdatabase.ViewModels;
import com.example.puzzlescitygame.roomdatabase.entity.User;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Edit_Profile extends AppCompatActivity  {
ActivityEditProfileBinding activityEditProfileBinding;
    ViewModels viewModel;
    String country;
    MediaPlayer mediaPlayer;

    private void startSound(){
        mediaPlayer = MediaPlayer.create(Edit_Profile.this, R.raw.clicked);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditProfileBinding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(activityEditProfileBinding.getRoot());
        CONTRE();

        viewModel = new ViewModelProvider(this).get(ViewModels.class);

        Intent intent = getIntent();
        int m = intent.getIntExtra("UserId", 0);
        Toast.makeText(Edit_Profile.this, ""+m, Toast.LENGTH_SHORT).show();

        String UserName = intent.getStringExtra("UserName");
        String UserEmail = intent.getStringExtra("UserEmail");
        String UserBirthdate = intent.getStringExtra("UserBirthdate");
        String UserGender = intent.getStringExtra("UserGender");

        activityEditProfileBinding.etUsername.setText(UserName);
        activityEditProfileBinding.etEmail.setText(UserEmail);
        activityEditProfileBinding.etBirthdate.setText(UserBirthdate);
        if (UserGender.equals(getString(R.string.male))) {
            activityEditProfileBinding.radiobuttonMale.setChecked(true);
        } else {
            activityEditProfileBinding.radiobuttonFemale.setChecked(true);
        }
//  ____________________________________________________________________________________________
// Datepicker
        activityEditProfileBinding.etBirthdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                activityEditProfileBinding.etBirthdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show(getSupportFragmentManager(), "date");
            }
        });
//  ___________________________________________________________________________________________
        activityEditProfileBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound();
                String name = activityEditProfileBinding.etUsername.getText().toString();
                String email = activityEditProfileBinding.etEmail.getText().toString();
                String birthDate = activityEditProfileBinding.etBirthdate.getText().toString();
                String radioGroup = null;
                if (activityEditProfileBinding.radiobuttonMale.isChecked()) {
                    radioGroup = "male";
                } else if (activityEditProfileBinding.radiobuttonFemale.isChecked()) {
                    radioGroup = "female";
                }
                assert radioGroup != null;
                User users = new User(name, email, birthDate, radioGroup, country);
                users.setIdUser(m);
                viewModel.updateUser(users);
                Intent intent = new Intent(Edit_Profile.this, Profile_Activity.class);
                intent.putExtra("id", users.getIdUser());
                startActivity(intent);
            }
        });

    }
//  ___________________________________________________________________________________________

    //Validation Stage

/*    String email = activityEditProfileBinding.etEmail.getText().toString();
    public static boolean userEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    String name = activityEditProfileBinding.etUsername.getText().toString();
    public boolean userName (String user_name){
        String regexName = "^(?=.{5,16}$)(?![.])(?![0-9])(?!.*[.]{2})[a-zA-Z0-9.]+(?<![.])$";
        String patternName = "(" + regexName + ")";
        boolean valid_user = user_name.matches(patternName);
        if(valid_user){return true;}
        else {return false;}
    }*/

    private void CONTRE() {
        ArrayList<String> list = new ArrayList<>();
        list.add(getString(R.string.Entercontrey));
        list.add(getString(R.string.Palestine));
        list.add(getString(R.string.Egypt));
        list.add(getString(R.string.Qatar));
        list.add(getString(R.string.SaudiArabia));
        list.add(getString(R.string.Algeria));
        list.add(getString(R.string.Kuwait));
        list.add(getString(R.string.Morocco));
        list.add(getString(R.string.Lebanon));
        list.add(getString(R.string.Jordan));
        list.add(getString(R.string.Sudan));
        list.add(getString(R.string.Tunisia));
        list.add(getString(R.string.Syrian));
        list.add(getString(R.string.Iraq));
        list.add(getString(R.string.Yemen));
        list.add(getString(R.string.Oman));
        list.add(getString(R.string.Algeria));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityEditProfileBinding.spinnerCountry.setAdapter(adapter);

        activityEditProfileBinding.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activityEditProfileBinding.spinnerCountry.setSelection(i, true);
                country = String.valueOf(adapterView.getItemAtPosition(i));
                Log.e("TAG", "GENDER: "+country );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}