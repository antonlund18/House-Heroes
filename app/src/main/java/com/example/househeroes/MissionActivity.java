package com.example.househeroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {

    private Hero hero;
    private QuestLog questLog;

    private ArrayList<String> questImages;
    private ArrayList<String> questNames;
    private ArrayList<String> questDesc;
    private ArrayList<String> questxp;
    private ArrayList<String> questGold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_log);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("hero", null);
        hero = gson.fromJson(json, Hero.class);
        questLog = hero.getQuestLog();

        initImageBitmaps();

    }

    public void initImageBitmaps(){
        //questImages = questLog.getQuestImages();
        questNames = questLog.getQuestNames();
        questDesc= questLog.getQuestDesc();
        questxp = questLog.getXp();
        questGold = questLog.getGold();

        initRecyclerView();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.questRecycler);
        MissionAdapter adapter = new MissionAdapter(this, hero, questNames, questDesc, questxp, questGold);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onPause() {
        super.onPause();

        // update views with your new hero object
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(hero);

        editor.putString("hero", json);
        editor.commit();
    }
}


