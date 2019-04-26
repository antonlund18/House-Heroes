package com.example.househeroes;

import java.util.ArrayList;

public class QuestLog {

    private ArrayList<Quest> questLog;
    private ArrayList<String> questNames;
    private ArrayList<String> questDesc;
    //private ArrayList<String> questImages;
    private ArrayList<String> xp;
    private ArrayList<String> gold;

    public QuestLog(){
        questLog = new ArrayList<>();
        questNames = new ArrayList<>();
        questDesc = new ArrayList<>();
        //questImages = new ArrayList<>();
        xp = new ArrayList<>();
        gold = new ArrayList<>();
    }

    public ArrayList<Quest> getQuestLog() {
        return questLog;
    }

    public ArrayList<String> getQuestNames() {
        return questNames;
    }

    public ArrayList<String> getQuestDesc() {
        return questDesc;
    }

    //public ArrayList<String> getQuestImages() {
    //    return questImages;
    //}

    public ArrayList<String> getXp() {
        return xp;
    }

    public ArrayList<String> getGold() {
        return gold;
    }

    public void addQuest(Quest q) {
        questLog.add(q);
        questNames.add(q.getTitle());
        questDesc.add(q.getDesc());
        //questImages.add(q.getImage());
        xp.add(String.valueOf(q.getXp()));
        gold.add(String.valueOf(q.getGold()));
    }

    public String printQuestLog() {
        StringBuilder builder = new StringBuilder();
        for(Quest q : questLog) {
            builder.append(q.getTitle() + "\n");
            builder.append(q.getDesc() + "\n");
            builder.append("Reward: " + q.getXp() + " XP & " + q.getGold() + " Gold \n");
            builder.append("\n");
        }
        return builder.toString();
    }
}
