package com.example.househeroes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {

    private Hero hero = Hero.getInstance();
    private QuestLog questLog = hero.getQuestLog();

    private ArrayList<String> questImages;
    private ArrayList<String> questNames;
    private ArrayList<String> questDesc;
    private ArrayList<String> questxp;
    private ArrayList<String> questGold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_log);

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
        MissionAdapter adapter = new MissionAdapter(this, questNames, questDesc, questxp, questGold);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


