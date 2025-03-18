package org.model.Characters;

public class Assassin extends AHero {
    public Assassin(String Name, int Level, int Xp, float Attack, float Defense, float Hp) {
        super(Name, "Assassin", Level, Xp, Attack, Defense, Hp);
        this.RunChange = 70;
    }

    @Override
    protected void applyLevelUpStats() {
        this.Attack += 8 + (this.Level * 2);
        this.Defense += 2 + (this.Level);
        this.MaxHp += 30 + (this.Level * 10);
    }
}
