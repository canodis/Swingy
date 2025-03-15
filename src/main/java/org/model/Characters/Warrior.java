package org.model.Characters;

public class Warrior extends AHero {
    public Warrior(String Name, int Level, float Xp, float Attack, float Defense, float Hp) {
        super(Name, "Warrior", Level, Xp, Attack, Defense, Hp);
    }

    @Override
    protected void applyLevelUpStats() {
        this.Attack += 5 + (this.Level * 1.5f);
        this.Defense += 5 + (this.Level * 1.5f);
        this.Hp += 50 + (this.Level * 15);
    }
}