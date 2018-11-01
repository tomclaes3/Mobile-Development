package com.example.tom.mineclicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HighscoreDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_detail);

        TextView rankTextView = findViewById(R.id.rank);
        TextView usernameTextView = findViewById(R.id.username);
        TextView clicksTextView = findViewById(R.id.clicks);
        TextView floorTextView = findViewById(R.id.floor);
        TextView countryTextView = findViewById(R.id.country);

        Intent intent = getIntent();
        String rank = intent.getStringExtra(HighscoreAdapter.KEY_RANK);
        String username = intent.getStringExtra(HighscoreAdapter.KEY_USERNAME);
        String clicks = intent.getStringExtra(HighscoreAdapter.KEY_CLICKS);
        String floor = intent.getStringExtra(HighscoreAdapter.KEY_FLOOR);
        String country = intent.getStringExtra(HighscoreAdapter.KEY_COUNTRY);


        System.out.println("LOGGING: " + clicks + " " + floor + " " + country);

        rankTextView.setText(rank);
        usernameTextView.setText(username);
        clicksTextView.setText(clicks);
        floorTextView.setText(String.valueOf(floor));
        countryTextView.setText(country);
    }
}