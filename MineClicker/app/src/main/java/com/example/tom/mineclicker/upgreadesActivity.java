package com.example.tom.mineclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class upgreadesActivity extends AppCompatActivity {


    Button mainButton;
    Button navigateToConacts;
    Button navigateToHighScores;
    Button buyButton;
    EditText currentClickDamage;
    EditText nextClickDamage;
    EditText gold;
    EditText price;
    UserModel user;
    UserDatabaseHelper databaseHelper = new UserDatabaseHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgreades);
        mainButton = (Button) findViewById(R.id.main);


        //navigatie buttons ophalen
        navigateToConacts = (Button) findViewById(R.id.contacts);
        navigateToHighScores = (Button) findViewById(R.id.highscores);
        buyButton = (Button) findViewById(R.id.upgreadeButton);
        currentClickDamage = findViewById(R.id.clickDamage);
        nextClickDamage = findViewById(R.id.clickDamageAfterUpgreade);

        //user init
        SharedPreferences prefs = getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        String name = prefs.getString("username", "No username defined");//"No name defined" is the default value.
        System.out.println("melding 5 " + name);
        user = databaseHelper.loadUserHandler(name);

        System.out.println("LOGGING: COUNTRY: " + user);


       //text fields invullen
        gold = (EditText) findViewById(R.id.coins);
        price = (EditText) findViewById(R.id.price);
        gold.setText(String.valueOf(user.getGold()));
       price.setText(String.valueOf(user.getClickDamage() * 2 ));
       int clickdamage = user.getClickDamage();
       String currentDamage = "Current click damage: " +  clickdamage;
       System.out.println("LOGGING: " + user.getClickDamage() + "-" + currentDamage);
       currentClickDamage.setText(currentDamage);
       nextClickDamage.setText("Next click Damage: " + String.valueOf(user.getClickDamage() * 1.50));
        //navigatie
        navigateToConacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upgreadesActivity.this, minersActivity.class));
            }
        });
        navigateToHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upgreadesActivity.this, HighscoreActivity.class));
            }
        });



        //upgreade
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > (user.getClickDamage()*2)) {
                    user.setGold(user.getGold() - (user.getClickDamage()*2));
                    double clickdamage = user.getClickDamage() * 1.5;
                    user.setClickDamage((int) clickdamage);
                    gold.setText(String.valueOf(user.getGold()));
                    gold.setText(String.valueOf(user.getGold()));
                    price.setText((user.getClickDamage() * 2 )+ "");
                    currentClickDamage.setText("Current click damage: " + user.getClickDamage());
                    nextClickDamage.setText("Next click Damage: " + (user.getClickDamage() * 1.50));
                }
            }
        });



        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(upgreadesActivity.this, MainActivity.class));
            }
        });


    }
}
