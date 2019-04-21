package com.example.househeroes;

import java.util.ArrayList;

public class AdventureList {
    private ArrayList<Adventure> alist;

    private ArrayList<String> aname;
    private ArrayList<String> aboss;
    private ArrayList<String> bosshp;
    private ArrayList<String> advxp;
    private ArrayList<String> advgold;
    private ArrayList<String> reqlvl;

    public AdventureList() {
        alist = new ArrayList<>();
        aname = new ArrayList<>();
        aboss = new ArrayList<>();
        bosshp = new ArrayList<>();
        advxp = new ArrayList<>();
        advgold = new ArrayList<>();
        reqlvl = new ArrayList<>();
    }

    public void addAdventure(Adventure adv) {
        alist.add(adv);
        aname.add(adv.getName());
        aboss.add(adv.getBoss());
        bosshp.add(String.valueOf(adv.getMaxhp()));
        advxp.add(String.valueOf(adv.getExp()));
        advgold.add(String.valueOf(adv.getGold()));
        reqlvl.add(String.valueOf(adv.getReqlvl()));

    }

    public ArrayList<String> getReqlvl() {
        return reqlvl;
    }

    public ArrayList<Adventure> getAlist() {
        return alist;
    }

    public ArrayList<String> getAname() {
        return aname;
    }

    public ArrayList<String> getAboss() {
        return aboss;
    }

    public ArrayList<String> getBosshp() {
        return bosshp;
    }

    public ArrayList<String> getAdvxp() {
        return advxp;
    }

    public ArrayList<String> getAdvgold() {
        return advgold;
    }
}
