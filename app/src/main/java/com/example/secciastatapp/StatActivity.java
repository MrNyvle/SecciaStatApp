package com.example.secciastatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.secciastatapp.databinding.ActivityStatsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StatActivity extends AppCompatActivity {
    ArrayList<Stat> stats;

    ListView ListItemStats;
    Button BackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        ListItemStats = findViewById(R.id.ListItemStats);
        BackButton = findViewById(R.id.BackButton);

        stats = new ArrayList<>();

        Map<String, String> paramV = new HashMap<>();
        paramV.put("RequestType", "GetStats");
        paramV.put("Email", Query.Email);

        Query.DatabaseQuery(paramV, FillList(), getApplicationContext());

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



    private Consumer<JSONArray> FillList(){

        return array -> {
            try {
                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    int Id = object.getInt("id");
                    String Title = object.getString("name");
                    int Likes = object.getInt("likes");
                    int Orders = object.getInt("orders");

                    stats.add(new Stat(Id, Title, Likes, Orders));
                }

                ListAdapter listAdapter = new ListAdapter(StatActivity.this,stats);
                Log.d("Debug", "FillList: 1");
                ListItemStats.setAdapter(listAdapter);
                Log.d("Debug", "FillList: 2 | ");

            } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        };
    }
}