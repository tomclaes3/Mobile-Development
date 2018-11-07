package com.example.tom.mineclicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    ImageView rockImage;
    ProgressBar hpBar;
    EditText coins;
    EditText previousFloor;
    EditText currentFloor;
    EditText nextFloor;

    Button navigateToConacts;
    Button navigateToShop;
    Button navigateToHighScores;
    Button navigateToUpgreade;
    UserModel user = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hpBar = (ProgressBar) findViewById(R.id.hpBar);
        rockImage = (ImageView) findViewById(R.id.rock);
        coins = (EditText) findViewById(R.id.coins);
        previousFloor = (EditText) findViewById(R.id.previousfloor);
        currentFloor = (EditText) findViewById(R.id.currentFloor);
        nextFloor = (EditText) findViewById(R.id.nextfloor);
        //navigatie buttons ophalen
        navigateToConacts = (Button) findViewById(R.id.contacts);
        navigateToShop = (Button) findViewById(R.id.shop);
        navigateToHighScores = (Button) findViewById(R.id.highscores);
        navigateToUpgreade = (Button) findViewById(R.id.upgrades);


        rockImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hpBar.setProgress(hpBar.getProgress() - 10);
                if (hpBar.getProgress() <= 0){
                    hpBar.setProgress(hpBar.getMax());
                    previousFloor.setText(String.valueOf(Integer.parseInt(previousFloor.getText().toString()) + 1));
                    currentFloor.setText(String.valueOf(Integer.parseInt(currentFloor.getText().toString()) + 1));
                    nextFloor.setText(String.valueOf(Integer.parseInt(nextFloor.getText().toString()) + 1));
                    coins.setText(String.valueOf(Integer.parseInt(coins.getText().toString()) + 1));
                }else{
                    //todo critically damage on the stone in a flashy epic way
                }
            }
        });
        //navigatie
        navigateToConacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, minersActivity.class));
            }
        });
        navigateToHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HighscoreActivity.class));
            }
        });
        navigateToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, shopActivity.class));
            }
        });
        navigateToUpgreade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, upgreadesActivity.class));
            }
        });


        //todo als je door gaat van register moet dit gebeuren
        SharedPreferences prefs = getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String name = prefs.getString("username", "No username defined");//"No name defined" is the default value.
            String password = prefs.getString("password", "no password defined"); //0 is the default value.
            user.setUsername(name);
            user.setPassword(password);
        }
System.out.println("logging:" + user.getUsername() + " " + user.getPassword());

    }
}
