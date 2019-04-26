package com.example.househeroes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Hero hero ;
    private Integer lvl;
    private Integer exp;
    private Integer xpNeeded;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hero = new Hero("BLE");

        initEnv();
        main();
    }

    public void main() {
        TextView showHeroName = (TextView) findViewById(R.id.heroName);
        TextView showLevel = (TextView) findViewById(R.id.level);
        TextView showExp = (TextView) findViewById(R.id.exp);
        TextView showNeededExp = (TextView) findViewById(R.id.neededExp);

        lvl = hero.getLvl();
        exp = hero.getExp();
        xpNeeded = (int) hero.expNeeded();

        showHeroName.setText(hero.getName());
        showLevel.setText("LEVEL: " + lvl.toString());
        showExp.setText(exp.toString());
        showNeededExp.setText(xpNeeded.toString() + " XP");
    }

    public void initEnv() {
        Shop shop = hero.getShop();
        QuestLog qlog = hero.getQuestLog();
        AdventureList alist = hero.getAlist();

        hero.goldGain(200);

        Item weapon1 = new Item("Stick", "Weapon", "https://i.redd.it/1sybw1lnwgh01.jpg", 1, 100);
        Item weapon2 = new Item("Butter Knife", "Weapon", "https://photos.gograph.com/thumbs/CSP/CSP466/cartoon-knife-vector-art_k22251607.jpg", 2, 250);
        Item weapon3 = new Item("Broken Sword", "Weapon", "https://photos.gograph.com/thumbs/CSP/CSP993/cartoon-knife-vector-art_k14875557.jpg", 4, 500);
        Item weapon4 = new Item("Basic Sword", "Weapon", "https://www.karatemart.com/images/products/large/medieval-squire-sword-1548898.jpg", 10, 1000);
        Item weapon5 = new Item("Epic Sword", "Weapon", "https://t3.rbxcdn.com/2d945ad0706fac8e5befb92919ed4017", 25, 5000);
        Item weapon6 = new Item("Legendary Sword", "Weapon", "https://t1.rbxcdn.com/186088aebc848fee77ed13f9d86fedeb", 50, 10000);

        Quest quest1 = new Quest("Empty the dishwasher", "The dishes are ready for battle. Go restock the supplies.", 1000, 50, 24 * 1000 * 60 * 60);
        Quest quest2 = new Quest("Vacuum", "The dust particles are loose! Use the vacuuminator 3000 to destroy them.", 2500, 200, 48 * 1000 * 60 * 60);
        Quest quest3 = new Quest("Clean your room", "A good strategy is key in victory. A messy room = a messy strategy.", 1500, 100, 1000 * 60 * 15);
        Quest quest4 = new Quest("Do laundry", "Can't go naked to battle!", 2000, 150, 5000);

        Adventure adventure1 = new Adventure("Laundry Mountain", "Angry Mom", 100, 100, 50, 0);
        Adventure adventure2 = new Adventure("Dust Dessert", "Germ Monster", 500, 200, 200, 5);
        Adventure adventure3 = new Adventure("Dish Dystopia", "Drain Devil", 1000, 500, 300, 10);
        Adventure adventure4 = new Adventure("Abeland", "Abefar", 10000, 10000, 10000, 20);

        alist.addAdventure(adventure1);
        alist.addAdventure(adventure2);
        alist.addAdventure(adventure3);
        alist.addAdventure(adventure4);


        shop.addItem(weapon1);
        shop.addItem(weapon2);
        shop.addItem(weapon3);
        shop.addItem(weapon4);
        shop.addItem(weapon5);
        shop.addItem(weapon6);

        qlog.addQuest(quest1);
        qlog.addQuest(quest2);
        qlog.addQuest(quest3);
        qlog.addQuest(quest4);

        hero.setShop(shop);
        hero.setQuestLog(qlog);
        hero.setAlist(alist);
    }

    public void openMissions(View view) {
        Intent openQL = new Intent(this, MissionActivity.class);
        startActivity(openQL);
    }

    public void openAttributesTab(View view) {
        Intent openAttributes = new Intent(this, attributes.class);
        startActivity(openAttributes);
    }

    public void openAdventureTab(View view) {
        Intent openAdventure = new Intent(this, AdventureTab.class);
        startActivity(openAdventure);
    }

    public void openInventoryTab(View view) {
        Intent openInventory = new Intent(this, InventoryActivity.class);
        startActivity(openInventory);
    }

    public void openShopTab(View view) {
        Intent openShop = new Intent(this, ShopActivity.class);
        startActivity(openShop);
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
