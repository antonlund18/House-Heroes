package com.example.househeroes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private Hero hero = Hero.getInstance();
    private Inventory inv = hero.getInv();

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
        InventoryAdapter adapter = new InventoryAdapter(this, mImages, mItemNames, mItemDamages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


