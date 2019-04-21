package com.example.househeroes;

import java.util.ArrayList;

public class Shop {
    private Hero hero = Hero.getInstance();

    private ArrayList<Item> shop;
    private ArrayList<String> images;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemDamages;
    private ArrayList<String> itemPrices;

    public Shop() {
        shop = new ArrayList<>();
        images = new ArrayList<>();
        itemNames = new ArrayList<>();
        itemDamages = new ArrayList<>();
        itemPrices = new ArrayList<>();
    }

    public void addItem(Item i) {
        shop.add(i);
        images.add(i.getImage());
        itemNames.add(i.getName());
        itemDamages.add(String.valueOf("Damage: " + i.getDamage()));
        itemPrices.add(String.valueOf(i.getPrice()));
    }



    public void removeItem(Item i) {
        shop.remove(shop.indexOf(i));
        images.remove(images.indexOf(String.valueOf(i.getImage())));
        itemNames.remove(itemNames.indexOf(i.getName()));
        itemPrices.remove((itemPrices.indexOf(String.valueOf(i.getPrice()))));
    }


    public void buyItem(Hero hero, Inventory inv, Item item) {
        if (hero.getGold() >= item.getPrice()) {
            inv.addItem(item);
            hero.goldGain(-item.getPrice());
            shop.remove(item);
        }
    }

    public ArrayList<Item> getShop() { return shop; }

    public ArrayList<String> getImages() { return images; }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }

    public ArrayList<String> getItemDamages() {
        return itemDamages;
    }

    public ArrayList<String> getItemPrices() {
        return itemPrices;
    }

    public String printShop() {
        StringBuilder b = new StringBuilder();
        for(Item i : shop) {
            b.append(i.getName() + "               " + i.getType() + "\n");
            b.append("Damage: " + i.getDamage() + "               Price: " + i.getPrice() + "\n");
            b.append("\n");
            b.append("\n");
        }
        return b.toString();
    }
}
