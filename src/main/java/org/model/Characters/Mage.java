package org.model.Characters;

public class Mage extends AHero{
    public Mage(String Name, int Level, float Xp, float Attack, float Defense, float Hp) {
        super(Name, "Mage", Level, Xp, Attack, Defense, Hp);
    }

    @Override
    protected void applyLevelUpStats() {
        this.Attack += 3 + (this.Level);
        this.Defense += 1 + (this.Level * 0.5f);
        this.Hp += 20 + (this.Level * 20);
    }
}
