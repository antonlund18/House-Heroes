package com.example.househeroes;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class attributes extends AppCompatActivity {
    private Hero ble = Hero.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributes);
        main();
    }

    public void main() {
        TextView aber = (TextView) findViewById(R.id.stats);
        aber.setText(ble.printAttributes());
    }

    public void skillStrength(View view) {
        ble.skillStrength();
        main();
    }
}
