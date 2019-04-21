package com.example.househeroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    private static final String TAG = "ShopActivity";

    private Hero hero = Hero.getInstance();
    private Shop shop = hero.getShop();

    private ArrayList<String> mImages;
    private ArrayList<String> mItemNames;
    private ArrayList<String> mItemDamages;
    private ArrayList<String> mItemPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Log.d(TAG, "onCreate: started");

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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImages, mItemNames, mItemDamages, mItemPrices);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
