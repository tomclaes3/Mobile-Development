package com.example.tom.mineclicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {


    Button navigatieToMain;
    Button navigatieToRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //navigatie buttons ophalen
        navigatieToMain = (Button) findViewById(R.id.LoginButton);
        navigatieToRegister = (Button) findViewById(R.id.RegisterButton);

        //navigatie
        navigatieToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        navigatieToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisteryActivity.class));
            }
        });
    }
}

