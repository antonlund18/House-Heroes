package com.example.househeroes;

import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class Item {
    private Hero hero = Hero.getInstance();
    private String name;
    private String image;
    private int damage;
    private int price;
    private String type;
    private boolean equipped;

    public Item(String name, String type, String image, int damage, int price) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.damage = damage;
        this.price = price;
        equipped = false;
    }

    public void equipItem() {
        Log.d(TAG, "equipItem: " + this.getDamage());
        hero.getEquipment().put(type, this);
        hero.setDmg();
    }


    public String getImage() { return image; }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public boolean isEquipped() {
        return equipped;
    }
}
