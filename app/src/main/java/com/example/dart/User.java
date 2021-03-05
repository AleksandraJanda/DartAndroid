package com.example.dart;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    int sets = 0;
    int legs = 0;
    int score = MainActivity.maxScore;
    boolean selected = false;
    List<Integer> avgList = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int sets, int legs, int score, List<Integer> avgList) {
        this.name = name;
        this.sets = sets;
        this.legs = legs;
        this.score = score;
        this.avgList = avgList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<Integer> getAvgList() {
        return avgList;
    }

    public void setAvgList(List<Integer> avgList) {
        this.avgList = avgList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", legs=" + legs +
                ", score=" + score +
                '}';
    }
}
