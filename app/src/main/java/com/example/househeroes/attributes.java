package com.example.househeroes;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class attributes extends AppCompatActivity {
    private Hero hero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributes);
        main();
    }

    public void main() {
        TextView aber = (TextView) findViewById(R.id.stats);
        aber.setText(hero.printAttributes());
    }

    public void skillStrength(View view) {
        hero.skillStrength();
        main();
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
