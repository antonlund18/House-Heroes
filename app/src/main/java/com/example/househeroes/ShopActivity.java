package com.example.househeroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    private static final String TAG = "ShopActivity";

    private Hero hero;
    private Shop shop;

    private ArrayList<String> mImages;
    private ArrayList<String> mItemNames;
    private ArrayList<String> mItemDamages;
    private ArrayList<String> mItemPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("hero", gson.toJson(hero));
        hero = gson.fromJson(json, Hero.class);
        shop = hero.getShop();

        TextView goldCoins = (TextView) findViewById(R.id.goldCoins);
        goldCoins.setText(String.valueOf(hero.getGold()));

        initImageBitmaps();
    }

    public void initImageBitmaps(){
        mImages = shop.getImages();
        mItemNames = shop.getItemNames();
        mItemDamages = shop.getItemDamages();
        mItemPrices = shop.getItemPrices();

        initRecyclerView();
    }

    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, hero, mImages, mItemNames, mItemDamages, mItemPrices);
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
