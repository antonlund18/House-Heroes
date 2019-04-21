package com.example.househeroes;

public class Quest {
    private Hero hero = Hero.getInstance();

    private String title;
    private String desc;
    private String image;
    private int xp;
    private int gold;
    private int cooldownms;
    private boolean avail;
    private boolean completed;

    public int getCooldownms() {
        return cooldownms;
    }

    public Quest(String title, String desc, int xp, int gold, int cooldownms) {
        this.title = title;
        this.desc = desc;
        //this.image = image;
        this.xp = xp;
        this.gold = gold;
        this.cooldownms = cooldownms;
        avail = true;
        completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public int getXp() {
        return xp;
    }

    public int getGold() { return gold; }

    public boolean isAvail() {
        return avail;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        hero.goldGain(gold);
        hero.expGain(xp);
    }
}
