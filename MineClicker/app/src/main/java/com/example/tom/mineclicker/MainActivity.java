package com.example.tom.mineclicker;

import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    private ImageView rockImage;
    private ProgressBar hpBar;
    private EditText coins;
    private EditText previousFloor;
    private EditText currentFloor;
    private EditText nextFloor;
    private CountDownTimer countDownTimer;
    private long timeLeftInMiliseconds = 60000;

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
        final Context mContext = this;
        UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(this);


        System.out.println("melding: test");

        SharedPreferences prefs = getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);

       // if (restoredText != null) {
            String name = prefs.getString("username", "No username defined");//"No name defined" is the default value.
            String password = prefs.getString("password", "no password defined"); //0 is the default value.
        String allusers = userDatabaseHelper.loadHandler();
           if ( allusers.contains(name)){
               user = userDatabaseHelper.loadUserHandler(name);
               System.out.println("melding: succesvol logged in");

           }else{

               user.setUsername(name);
               user.setPassword(password);
               userDatabaseHelper.addHandler(user);
               System.out.println("melding: succesvol registerd");
           }
       // }
        System.out.println("logging:" + user.getUsername() + " " + user.getPassword());






        rockImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hpBar.setProgress(hpBar.getProgress() - 10);
                rockImage.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shakeanimation));
                if (hpBar.getProgress() <= 0) {
                    hpBar.setProgress(hpBar.getMax());
                    previousFloor.setText(String.valueOf(Integer.parseInt(previousFloor.getText().toString()) + 1));
                    currentFloor.setText(String.valueOf(Integer.parseInt(currentFloor.getText().toString()) + 1));
                    nextFloor.setText(String.valueOf(Integer.parseInt(nextFloor.getText().toString()) + 1));
                    coins.setText(String.valueOf(Integer.parseInt(coins.getText().toString()) + 1));
                } else {
                    //todo critically damage on the stone in a flashy epic way
                }
                //rockImage.setRotation(rockImage.getRotation() - 15);
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
    }
    public void StartTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMiliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }
}
