package com.isyed.greenflagv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAccount = findViewById(R.id.btn_create_account);

        createAccount.setOnClickListener(e->{
            Intent intent  = new Intent(this, CreateAccount.class );
            startActivity(intent);
        });
    }
}