package com.example.househeroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AdventureTab extends AppCompatActivity {

    private Hero hero;
    private AdventureList alist = hero.getAlist();
    private RecyclerView recyclerView;

    private ArrayList<String> questImages;
    private ArrayList<String> aname;
    private ArrayList<String> aboss;
    private ArrayList<String> bosshp;
    private ArrayList<String> advxp;
    private ArrayList<String> advgold;
    private ArrayList<String> reqlvl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_tab);

      initImageBitmaps();

    }

    public void initImageBitmaps(){
        //questImages = questLog.getQuestImages();
        aname = alist.getAname();
        aboss = alist.getAboss();
        bosshp = alist.getBosshp();
        advxp = alist.getAdvxp();
        advgold = alist.getAdvgold();
        reqlvl = alist.getReqlvl();

        initRecyclerView();
    }

    public void initRecyclerView() {
        recyclerView = findViewById(R.id.adventureRecycler);
        AdventureAdapter adapter = new AdventureAdapter(this, hero, aname, aboss, bosshp, advxp, advgold, reqlvl);
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

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("hero", gson.toJson(hero));
        hero = gson.fromJson(json, Hero.class);
    }

}








    /*
    Thread t = new Thread() {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(1000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startAdventure();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_tab);

        adv = new Adventure("Laundry Mountain", "Angry Mom", 100, 100, 50);
        String advText = adv.printAdventure();

        // Show adventure
        TextView showHeroName = (TextView)
                findViewById(R.id.Adventure1);
        showHeroName.setText(advText);
    }

    public void main() {
        String advText = adv.printAdventure();
        TextView showHeroName = (TextView)
                findViewById(R.id.Adventure1);
        showHeroName.setText(advText);
    }

    public void startButton(View view) {
        t.start();
    }
    public void startAdventure() {
        if(!adv.isComplete()) {
            adv.startAdventure(hero.getDmg());
            String advText = adv.printAdventure();

            TextView showHeroName = (TextView)
                    findViewById(R.id.Adventure1);
            showHeroName.setText(advText);
        } else {
            t.interrupt();
        }
    }
    */