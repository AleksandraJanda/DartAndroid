package com.example.dart;

public class User {
    String name;
    int sets = 0;
    int legs = 0;
    int score = MainActivity.maxScore;
    boolean selected = false;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int sets, int legs, int score) {
        this.name = name;
        this.sets = sets;
        this.legs = legs;
        this.score = score;
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
