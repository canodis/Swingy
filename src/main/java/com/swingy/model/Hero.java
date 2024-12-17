package com.swingy.model;

public class Hero {
    private final String name;
    private final String heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;

    public Hero(String name, String heroClass, int attack, int defense, int hitPoints) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = 1;
        this.experience = 0;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void gainExperience(int points) {
        this.experience += points;
        if (this.experience >= this.level * 1000 + (this.level - 1) * (this.level - 1) * 450) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.attack += 5;
        this.defense += 3;
        this.hitPoints += 10;
        System.out.println("Congratulations! " + this.name + " leveled up to level " + this.level);
    }
}
