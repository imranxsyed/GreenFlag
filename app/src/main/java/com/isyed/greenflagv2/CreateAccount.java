package com.isyed.greenflagv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.isyed.greenflagv2.utilities.EmailPasswordValidations;

public class CreateAccount extends AppCompatActivity {

    private TextInputEditText emailAddress;
    private TextInputEditText password;
    private TextInputEditText repeatPassword;
    private Button nextPage;
    private Drawable tick_icon;
    private Drawable cross_icon;
    TextView erroMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailAddress = findViewById(R.id.tiet_username);
        password = findViewById(R.id.tiet_password);
        repeatPassword = findViewById(R.id.tiet_repeat_pass);
        nextPage = findViewById(R.id.btn_next);
        nextPage.setBackground(getDrawable(R.drawable.gradient_button_background));
        erroMessage = findViewById(R.id.tv_error_message);
        tick_icon = getDrawable(R.drawable.tick);
        cross_icon = getDrawable(R.drawable.cross);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);



        emailAddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //email is empty or becomes empty
                if (s.length() <= 0){

                    //set the correct border
                    emailAddress.setBackground(getBaseContext().getDrawable(R.drawable.border));
                    //hide message
                    erroMessage.setVisibility(View.INVISIBLE);

                    //clear password field and disable it
                    password.getText().clear();
                    password.setEnabled(false);

                    //clear field icon
                    emailAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
                    return;

                }
                boolean isValidEmail = EmailPasswordValidations.isEmailValid(s.toString());

                if (isValidEmail){

                    //removing cross icon and adding tick icon for valid email
                    emailAddress.setCompoundDrawablesRelativeWithIntrinsicBounds
                            (null,null,tick_icon,null);
                    //adding green icon
                    emailAddress.setBackground(getBaseContext().getDrawable(R.drawable.green_border));


                    //enabling password field
                    password.setEnabled(true);
                    //hide the error message
                    erroMessage.setVisibility(View.INVISIBLE);
                }
                else{
                    emailAddress.setCompoundDrawablesRelativeWithIntrinsicBounds
                            (cross_icon,null,null,null);

                    //show the appopriate message
                    erroMessage.setText("The Email is not Valid");
                    erroMessage.setVisibility(View.VISIBLE);
                    //clear password field and disable it
                    password.getText().clear();
                    password.setEnabled(false);

                    //set the border color properly

                    emailAddress.setBackground(getBaseContext().getDrawable(R.drawable.red_border));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //if it is empty or becomes empty
                if (null == s || s.toString().isEmpty()){
                    //hide error message
                    erroMessage.setVisibility(View.INVISIBLE);

                    //empty and disabled the repeat password field
                    repeatPassword.getText().clear();
                    repeatPassword.setEnabled(false);

                    //the state of the password field when the it is cleared
                    password.setBackground(getDrawable(R.drawable.border));
                    password.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);

                    return;
                }



                boolean isPasswordValid = EmailPasswordValidations.checkPasswordCritria(s.toString());

                if (isPasswordValid){
                    //adding tick icon and removing others
                    password.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,tick_icon,null);
                    //icon
                    password.setBackground(getDrawable(R.drawable.green_border));
                    //enabling passwords
                    repeatPassword.setEnabled(true);
                    erroMessage.setVisibility(View.INVISIBLE);

                }
                else{
                    erroMessage.setVisibility(View.VISIBLE);
                    erroMessage.setText("You Password does not match requiremet criteria");

                    //empty and disabled the repeat password field
                    repeatPassword.getText().clear();
                    repeatPassword.setEnabled(false);



                    password.setCompoundDrawablesRelativeWithIntrinsicBounds(cross_icon,null,null,null);
                    password.setBackground(getDrawable(R.drawable.red_border));

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {   }

            @Override
            public void afterTextChanged(Editable s) {       }

        });

        repeatPassword.addTextChangedListener(new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //if the checkbox is empty
                if (null == s || s.toString().isEmpty()){

                    erroMessage.setVisibility(View.INVISIBLE);
                    nextPage.setEnabled(false);

                    repeatPassword.setBackground(getDrawable(R.drawable.border));
                    repeatPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);


                    return;
                }
                String pass = password.getText().toString();
                String repeatPass = s.toString();

                //if password matches
                if (pass.equals(repeatPass)){
                    repeatPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,tick_icon,null);
                    repeatPassword.setBackground(getDrawable(R.drawable.green_border));
                    //hide message
                    erroMessage.setVisibility(View.INVISIBLE);
                    //enable next button
                    nextPage.setEnabled(true);

                }
                else{
                    repeatPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,cross_icon,null);
                    repeatPassword.setBackground(getDrawable(R.drawable.red_border));

                    //set the appropriate error message
                    erroMessage.setText("Your Passwords Don't match");
                    erroMessage.setVisibility(View.VISIBLE);
                    nextPage.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {  }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        });
        
        nextPage.setOnClickListener(e ->{

            //getInfo From fields
            String email  = emailAddress.getEditableText().toString();
            String pass = password.getEditableText().toString();

            //clear fields
            emailAddress.getEditableText().clear();
            password.getEditableText().clear();
            repeatPassword.getEditableText().clear();

            startActivity(new Intent(this,AddMoreInfo.class));
        });

    }


}