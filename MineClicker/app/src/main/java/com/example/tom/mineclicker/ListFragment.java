package com.example.tom.mineclicker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ListFragment extends Fragment {

  private static final String URL_DATA = "http://5bc77c0dcc83760013c1cd15.mockapi.io/highscores";
  RecyclerView recyclerView;
  RecyclerView.Adapter adapter;
  List<HighscoreModel> highscoreList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_list_fragment, container, false);

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    recyclerView = getView().findViewById(R.id.recyclerView);

    recyclerView.setAdapter(null);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    highscoreList = new ArrayList<>();


    loadUrlData();

  }

  private void loadUrlData() {
    final ProgressDialog progressDialog = new ProgressDialog(getActivity());

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

          adapter = new HighscoreAdapter(highscoreList, getActivity());
          recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_LONG).show();
      }
    });

    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(stringRequest);
  }
}
