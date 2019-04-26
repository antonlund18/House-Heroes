package com.example.househeroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private Hero hero;
    private Inventory inv;

    private ArrayList<String> mImages;
    private ArrayList<String> mItemNames;
    private ArrayList<String> mItemDamages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        initImageBitmaps();

    }

    public void initImageBitmaps(){
        mImages = inv.getImages();
        mItemNames = inv.getItemNames();
        mItemDamages = inv.getItemDamages();

        initRecyclerView();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerInventory);
        InventoryAdapter adapter = new InventoryAdapter(this, hero, mImages, mItemNames, mItemDamages);
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
        inv = hero.getInv();
    }
}


