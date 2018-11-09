package com.example.tom.mineclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

public class minersActivity extends AppCompatActivity {


    Button mainButton;
    Button navigateToShop;
    Button navigateToHighScores;
    Button navigateToUpgreade;
    Button buy1;
    Button buy2;
    Button buy3;
    Button buy4;
    Button buy5;
    Button buy6;
    EditText price1;
    EditText price2;
    EditText price3;
    EditText price4;
    EditText price5;
    EditText price6;
    EditText gold;


    UserModel user;
    UserDatabaseHelper databaseHelper = new UserDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miners);
        mainButton = (Button) findViewById(R.id.main);
        buy1 = (Button) findViewById(R.id.minerBuy1);
        buy2 = (Button) findViewById(R.id.minerBuy2);
        buy3 = (Button) findViewById(R.id.minerBuy3);
        buy4 = (Button) findViewById(R.id.minerBuy4);
        buy5 = (Button) findViewById(R.id.minerBuy5);
        buy6 = (Button) findViewById(R.id.minerBuy6);
        price1 = (EditText) findViewById(R.id.minerPrice1);
        price2 = (EditText) findViewById(R.id.minerPrice2);
        price3 = (EditText) findViewById(R.id.minerPrice3);
        price4 = (EditText) findViewById(R.id.minerPrice4);
        price5 = (EditText) findViewById(R.id.minerPrice5);
        price6 = (EditText) findViewById(R.id.minerPrice6);
        gold = (EditText) findViewById(R.id.coins);



        SharedPreferences prefs = getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        String name = prefs.getString("username", "No username defined");//"No name defined" is the default value.
        user = databaseHelper.loadUserHandler(name);
        gold.setText(String.valueOf(user.getGold()));
        checkOrBoughtUpgraede();

        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > 50) {
                    user.setGold(user.getGold() - 50);
                    user.setDps(10);
                    checkOrBoughtUpgraede();
                    gold.setText(String.valueOf(user.getGold()));
                    saveUser();
                }
            }
        });
        buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > 150) {
                    user.setGold(user.getGold() - 150);
                    user.setDps(20);
                    checkOrBoughtUpgraede();
                    gold.setText(String.valueOf(user.getGold()));
                    saveUser();
                }
            }
        });
        buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > 300) {
                    user.setGold(user.getGold() - 300);
                    user.setDps(40);
                    checkOrBoughtUpgraede();
                    gold.setText(String.valueOf(user.getGold()));
                    saveUser();
                }
            }
        });
        buy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > 500) {
                    user.setGold(user.getGold() - 500);
                    user.setDps(80);
                    checkOrBoughtUpgraede();
                    gold.setText(String.valueOf(user.getGold()));
                    saveUser();
                }
            }
        });
        buy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > 1000) {
                    user.setGold(user.getGold() - 1000);
                    user.setDps(160);
                    checkOrBoughtUpgraede();
                    gold.setText(String.valueOf(user.getGold()));
                    saveUser();
                }
            }
        });
        buy6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGold() > 50) {
                    user.setGold(user.getGold() - 2000);
                    user.setDps(320);
                    checkOrBoughtUpgraede();
                    gold.setText(String.valueOf(user.getGold()));
                    saveUser();
                }
            }
        });


        //navigatie buttons ophalen
        navigateToShop = (Button) findViewById(R.id.shop);
        navigateToHighScores = (Button) findViewById(R.id.highscores);
        navigateToUpgreade = (Button) findViewById(R.id.upgrades);

        //navigatie
        navigateToHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(minersActivity.this, HighscoreActivity.class));
            }
        });
        navigateToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(minersActivity.this, shopActivity.class));
            }
        });
        navigateToUpgreade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(minersActivity.this, upgreadesActivity.class));
            }
        });



        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
                startActivity(new Intent(minersActivity.this, MainActivity.class));
            }
        });

    }
    public void checkOrBoughtUpgraede(){
        switch (user.getDps()){
            case 10:
                buy1.setEnabled(false);
                price1.setText("bought");
                break;
            case 20:
                buy1.setEnabled(false);
                buy2.setEnabled(false);
                price1.setText("bought");
                price2.setText("bought");
                break;
            case 40:
                buy1.setEnabled(false);
                buy2.setEnabled(false);
                buy3.setEnabled(false);
                price1.setText("bought");
                price2.setText("bought");
                price3.setText("bought");
                break;
            case 80:
                buy1.setEnabled(false);
                buy3.setEnabled(false);
                buy4.setEnabled(false);
                buy2.setEnabled(false);
                price1.setText("bought");
                price2.setText("bought");
                price3.setText("bought");
                price4.setText("bought");
                break;
            case 160:
                buy1.setEnabled(false);
                buy2.setEnabled(false);
                buy3.setEnabled(false);
                buy4.setEnabled(false);
                buy5.setEnabled(false);
                price1.setText("bought");
                price3.setText("bought");
                price4.setText("bought");
                price5.setText("bought");
                price2.setText("bought");

                break;
            case 320:
                buy1.setEnabled(false);
                buy2.setEnabled(false);
                buy3.setEnabled(false);
                buy4.setEnabled(false);
                buy5.setEnabled(false);
                buy6.setEnabled(false);
                price1.setText("bought");
                price2.setText("bought");
                price3.setText("bought");
                price4.setText("bought");
                price5.setText("bought");
                price6.setText("bought");
                break;
        }
    }
    public void saveUser(){
        databaseHelper.updateHandler(user);
    }
}
