package com.example.tom.mineclicker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://5bc77c0dcc83760013c1cd15.mockapi.io/highscores";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<HighscoreModel> highscoreList;

    Button mainButton;

    Button navigateToConacts;
    Button navigateToShop;
    Button navigateToUpgreade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(null);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        highscoreList = new ArrayList<>();



        mainButton = (Button) findViewById(R.id.main);


        //navigatie buttons ophalen
        navigateToConacts = (Button) findViewById(R.id.contacts);
        navigateToShop = (Button) findViewById(R.id.shop);
        navigateToUpgreade = (Button) findViewById(R.id.upgrades);


        //navigatie
        navigateToConacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighscoreActivity.this, minersActivity.class));
            }
        });
        navigateToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighscoreActivity.this, shopActivity.class));
            }
        });
        navigateToUpgreade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HighscoreActivity.this, upgreadesActivity.class));
            }
        });



        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(HighscoreActivity.this, MainActivity.class));
            }
        });

        loadUrlData();

    }

    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject highscore = array.getJSONObject(i);
                        HighscoreModel highscores = new HighscoreModel(highscore.getInt("rank"),
                                highscore.getString("username"), highscore.getInt("floor"),
                                highscore.getInt("clicks"), highscore.getString("country"));
                        highscoreList.add(highscores);
                    }

                    adapter = new HighscoreAdapter(highscoreList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HighscoreActivity.this, "Error" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
