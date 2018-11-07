package com.example.tom.mineclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HighscoreDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_detail);

        TextView rankTextView = findViewById(R.id.rank);
        TextView usernameTextView = findViewById(R.id.usernameLabel);
        TextView clicksTextView = findViewById(R.id.clicks);
        TextView floorTextView = findViewById(R.id.floor);
        TextView countryTextView = findViewById(R.id.country);

        Intent intent = getIntent();
        String rank = intent.getStringExtra(HighscoreAdapter.KEY_RANK);
        String username = intent.getStringExtra(HighscoreAdapter.KEY_USERNAME);
        String clicks = intent.getStringExtra(HighscoreAdapter.KEY_CLICKS);
        String floor = intent.getStringExtra(HighscoreAdapter.KEY_FLOOR);
        String country = intent.getStringExtra(HighscoreAdapter.KEY_COUNTRY);

        rankTextView.setText(rank);
        usernameTextView.setText(username);
        clicksTextView.setText(clicks);
        floorTextView.setText(String.valueOf(floor));
        countryTextView.setText(country);
    }
}
