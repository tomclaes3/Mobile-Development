package com.example.tom.mineclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisteryActivity extends AppCompatActivity {

     Button navigatieNaarLogin;
     Button bevestigRegisratieEnNavigeerNaarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registery);


        //navigatie buttons ophalen
        navigatieNaarLogin = (Button) findViewById(R.id.BackButton);
        bevestigRegisratieEnNavigeerNaarMain = (Button) findViewById(R.id.register_Button);

        //navigatie
        navigatieNaarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisteryActivity.this, LoginActivity.class));
            }
        });
        bevestigRegisratieEnNavigeerNaarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = ((EditText) findViewById(R.id.Password)).getText().toString();
                String rePassword = ((EditText) findViewById(R.id.RepeatPassword)).getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences("ACCOUNT", MODE_PRIVATE).edit();

                if ( password.equals(rePassword) ) {
                    editor.putString("username", ((EditText) findViewById(R.id.UserName)).getText().toString());
                    editor.putString("password", password);
                    editor.apply();

                    startActivity(new Intent(RegisteryActivity.this, MainActivity.class));
                } else {
                    ((EditText) findViewById(R.id.UserName)).setText("passwoorden komen niet overeen!");
                }
            }


        });



    }
}
