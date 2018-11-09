package com.example.tom.mineclicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

public class minersActivity extends AppCompatActivity {


    Button mainButton;
    Button navigateToShop;
    Button navigateToHighScores;
    Button navigateToUpgreade;
    RecyclerView recyclerView;
    String[] items={"item 0", "item 1", "item 2","item 3","item 4"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miners);
        mainButton = (Button) findViewById(R.id.main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMiners);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new minerAdapter(this,items));

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
}
