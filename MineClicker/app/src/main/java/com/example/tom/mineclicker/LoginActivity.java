package com.example.tom.mineclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {


    Button navigatieToMain;
    Button navigatieToRegister;
    Button loginbutton;
    UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(this);
    UserModel user = new UserModel();

    //maak een methode die chekt of de user al bestaad zo ja komt hij van de login pagina zo nee komt hij van de register pagina (plaats on resum in on create)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //navigatie buttons ophalen
        navigatieToMain = (Button) findViewById(R.id.LoginButton);
        navigatieToRegister = (Button) findViewById(R.id.RegisterButton);
        loginbutton = (Button) findViewById(R.id.LoginButton);

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
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText)findViewById(R.id.loginUsernameText)).getText().toString();

                String allusers = userDatabaseHelper.loadHandler();
                System.out.println("de usrname is " + username + "en " + allusers);
                if ((allusers.contains(username))){
                    String password = ((EditText) findViewById(R.id.loginPassword)).getText().toString();
                    user = userDatabaseHelper.loadUserHandler(username);
                    if(user.getPassword().equals(password)){
                        //password correct, stuur door naar main
                        SharedPreferences.Editor editor = getSharedPreferences("ACCOUNT", MODE_PRIVATE).edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }else {
                        ((EditText)findViewById(R.id.errortext)).setText("De username of password is incorrect!");
                    }
                }else{
                    ((EditText)findViewById(R.id.errortext)).setText("De username kan niet gevonden worden, probeer nog eens");
                }






            }


        });
    }

}

