package com.example.househeroes;

public class Adventure {
    private String name, boss;
    private int exp, gold, cooldown, currenthp, maxhp, reqlvl;
    private boolean complete, avail;

    public Adventure(String name, String boss, int hp, int exp, int gold, int reqlvl) {
        this.name = name;
        this.boss = boss;
        this.exp = exp;
        this.gold = gold;
        complete = false;
        maxhp = hp;
        currenthp = maxhp;
        avail = true;
        cooldown = 0;     // Pass this variable in const later
        this.reqlvl = reqlvl;
    }

    public int getReqlvl() {
        return reqlvl;
    }

    public String getName() {
        return name;
    }

    public String getBoss() {
        return boss;
    }

    public int getExp() {
        return exp;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public boolean isAvail() {
        return avail;
    }

    public int getGold() {
        return gold;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setCurrenthp(int currenthp) {
        this.currenthp = currenthp;
    }

    public int getCurrenthp() {
        return currenthp;
    }

    public String printAdventure() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + "\n");
        builder.append("Boss: " + boss + "\n");
        builder.append(currenthp + " / " + maxhp + " HP \n");
        builder.append("Rewards: " + exp + " XP & " + gold + " Gold \n");
        return builder.toString();
    }

    public void completeAdv(Hero hero) {
            hero.expGain(exp);
            hero.goldGain(gold);
    }

}
