package com.example.househeroes;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    private static Hero hero = new Hero("BLE");
    private String name;        // Name of hero
    private Map<String, Object> attributes;
    private Map<String, Item> equipment;
    private Shop shop;
    private int exp;                                // Current XP
    private int gold;                               // Current gold
    private int lvl;                                // Current level
    private int hp;                                 // Current health points
    private int maxhp;                              // Max health points
    private int mp;             // Current mana points
    private int maxmp;          // Max mana points
    private int dmg;         // Current physical damage
    private int skillPoints;    // Unspent skillpoints
    private int vitality;       // Raises HP
    private int strength;       // Raises physical damage
    private int agility;        // Lowers cooldown
    private int magic;          // Raises magic damage
    private int charisma;       // Raises XP gained
    private int luck;           // Raises chance of getting loot
    private QuestLog questLog;
    private Inventory inv;
    private AdventureList alist;

    private Hero(String name) {
        this.name = name;
        inv = new Inventory();
        shop = new Shop();
        alist = new AdventureList();
        exp = 0;
        lvl = 1;
        hp = 50;
        maxhp = 50;
        mp = 20;
        maxmp = 20;
        dmg = 2;
        skillPoints = 10;
        vitality = 0;
        strength = 0;
        agility = 0;
        magic = 0;
        charisma = 0;
        luck = 0;
        equipment = new HashMap<>();
        equipment.put("Weapon", null);
        equipment.put("Chest", null);
        equipment.put("Legs", null);
        equipment.put("Gloves", null);
        equipment.put("Head", null);
        questLog = new QuestLog();
    }


    public static Hero getInstance() {
        return hero;
    }

    public Map<String, Item> getEquipment() {
        return equipment;
    }

    public Inventory getInv() {
        return inv;
    }

    public Shop getShop() {
        return shop;
    }

    public String getName() {
        return name;
    }

    public QuestLog getQuestLog() {
        return questLog;
    }

    public int getExp() {
        return exp;
    }

    public int getLvl() {
        return lvl;
    }

    public AdventureList getAlist() {
        return alist;
    }

    public int getVitality() {
        return vitality;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getMagic() {
        return magic;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getLuck() {
        return luck;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getGold() {
        return gold;
    }

    public void expGain(int amount) {
        exp += amount;
        for(int x = 0; x < 1000; x++) {
            if(exp > expNeeded()) {
                exp -= expNeeded();
                ding();
            }
        }
    }

    public void goldGain(int amount) {
        gold += amount;
    }

    public long expNeeded() {
        return 5*Math.round((1000 * Math.pow(1.2, lvl-1))/5);
    }

    public void ding() {
        lvl += 1;
    }

    public String printAttributes() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: " + name + "\n");
        builder.append("Level: " + lvl + "\n");
        builder.append("XP: " + exp  + " / " + expNeeded() + "\n");
        builder.append("Gold: " + gold + "\n");
        builder.append("Health points: " + hp + " / " + maxhp + "\n");
        builder.append("Mana points: " + mp + " / " + maxmp + "\n");
        builder.append("Damage: " + dmg + "\n");
        builder.append("\n");
        builder.append("Unspent skill points: " + skillPoints + "\n");
        builder.append("Vitality: " + vitality + "\n");
        builder.append("Strength: " + strength + "\n");
        builder.append("Agility: " + agility + "\n");
        builder.append("Magic: " + magic + "\n");
        builder.append("Charisma: " + charisma + "\n");
        builder.append("Luck: " + luck + "\n");
        return builder.toString();
    }

    public void setDmg() {
        if(equipment.get("Weapon") != null) {
            dmg = 2 + strength / 2 + equipment.get("Weapon").getDamage();
        }
    }

    public void skillVitality() {
        if(skillPoints > 0) {
            vitality += 1;
            skillPoints -= 1;
            hp += 1;
            maxhp += 1;
        }
    }

    public void skillStrength() {
        if(skillPoints > 0) {
            strength += 1;
            skillPoints -= 1;
            dmg += 0.5;
        }
    }

    public void skillAgility() {
        if(skillPoints > 0) {
            agility += 1;
            skillPoints -= 1;
        }
    }

    public void skillMagic() {
        if(skillPoints > 0) {
            magic += 1;
            skillPoints -= 1;
        }
    }

    public void skillCharisma() {
        if(skillPoints > 0) {
            charisma += 1;
            skillPoints -= 1;
        }
    }

    public void skillLuck() {
        if (skillPoints > 0) {
            luck += 1;
            skillPoints -= 1;
        }
    }

}
