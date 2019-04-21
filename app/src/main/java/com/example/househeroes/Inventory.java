package com.example.househeroes;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inv;

    private Hero hero = Hero.getInstance();

    private ArrayList<String> images;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemDamages;

    public Inventory() {
        inv = new ArrayList<>();
        images = new ArrayList<>();
        itemNames = new ArrayList<>();
        itemDamages = new ArrayList<>();
    }

    public void addItem(Item i) {
        inv.add(i);
        images.add(i.getImage());
        itemNames.add(i.getName());
        itemDamages.add(String.valueOf("Damage: " + i.getDamage()));
    }

    public void equipItem(Item i) {
        i.equipItem();
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }

    public ArrayList<String> getItemDamages() {
        return itemDamages;
    }

    public ArrayList<Item> getInventory() {
        return inv;
    }

    public String printInventory() {
        StringBuilder builder = new StringBuilder();
        for(Item i : inv) {
            builder.append(i.getName() + "               " + i.getType() + "\n");
            builder.append("Damage: " + i.getDamage() + "\n");
            builder.append("\n");
            builder.append("\n");
        }
        return builder.toString();
    }
}
