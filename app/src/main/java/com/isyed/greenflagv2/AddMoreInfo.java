package com.isyed.greenflagv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.isyed.greenflagv2.fragments.CountryFragment;
import com.isyed.greenflagv2.fragments.DateFragment;

import java.time.Year;
import java.util.Calendar;

public class AddMoreInfo extends AppCompatActivity
        implements CountryFragment.CountryFragmentListener , DateFragment.DateFragmentListener{

    Button saveButton;
    EditText etUserCountry;
    EditText etUserGender;
    RadioGroup rgGender;
    Button btnUserAge;
    EditText etUserAge;
    private DateFragment dateFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_info);

        saveButton = findViewById(R.id.btn_save);
        etUserCountry = findViewById(R.id.et_country);
        etUserGender = findViewById(R.id.et_user_gender);
        rgGender = findViewById(R.id.rg_gender);
        btnUserAge = findViewById(R.id.btn_user_birth_date);
        etUserAge = findViewById(R.id.et_user_age);


        etUserCountry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    new CountryFragment().show(getSupportFragmentManager(),"COUNTRY PICKER");
                }
            }
        });
        
        saveButton.setOnClickListener(e->{

        });

        rgGender.setOnCheckedChangeListener( (group,checkedId) -> {

            switch (checkedId){

                case R.id.rbtn_male:
                    etUserGender.setText("Male");
                    break;

                case R.id.rbtn_female:
                    etUserGender.setText("Female");
                    break;

                case R.id.rbtn_ns:
                    etUserGender.setText("Not Specified");
                    break;

                default:break;
            }
        });

        btnUserAge.setOnClickListener( view -> {

           if (dateFragment == null){
               dateFragment =  new DateFragment();
           }

           dateFragment.show(getSupportFragmentManager(),"DATE PICKER");
        });

    }

    @Override
    public void passData(String country) {

        //if some country is selected
        if(!CountryFragment.CANCEL.equals(country)){
            etUserCountry.setText(country);
        }

        etUserCountry.clearFocus();
    }


    @Override
    public void transferAge(String age) {
        etUserAge.setText(age);
    }
}