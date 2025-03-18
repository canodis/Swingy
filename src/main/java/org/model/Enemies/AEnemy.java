package org.model.Enemies;

import org.data.Vector2;
import org.model.Characters.AHero;
import org.model.GameObject;

public abstract class AEnemy extends GameObject {
    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
        LEGENDARY
    }

    protected int Level;
    protected String Name;
    protected float Attack;
    protected float Defense;
    protected float Hp;
    protected Rarity rarity;

    protected AEnemy(int level, float attack, float defense, float hp) {
        this.Level = level;
        this.Attack = attack;
        this.Defense = defense;
        this.Hp = hp;
        this.Icon = "E";
        this.isVisible = true;
        this.rarity = Rarity.COMMON;
    }

    public double getWeight() {
        return switch (rarity) {
            case COMMON -> 30;
            case UNCOMMON -> 20;
            case RARE -> 10;
            case EPIC -> 5;
            case LEGENDARY -> 1;
            default -> 0;
        };
    }

    public float getRarityMultiplier() {
        return switch (rarity) {
            case UNCOMMON -> 1.2f;
            case RARE -> 1.6f;
            case EPIC -> 2.0f;
            case LEGENDARY -> 3.0f;
            default -> 1.0f;
        };
    }

    protected float scaleStat(float baseValue, float level) {
        return (float)(baseValue * Math.pow(1.15, level - 1) * getRarityMultiplier());
    }

    public abstract AEnemy Clone(int Level);

    public int GetLevel() { return Level; }
    public void SetLevel(int level) { Level = level; }

    public float GetAttack() {
        float attack = this.Attack;
        attack += (float) ((Math.random() * 0.2 - 0.1) * attack);
        return attack;
    }
    public void SetAttack(float attack) { Attack = attack; }

    public float GetDefense() { return Defense; }
    public void SetDefense(float defense) { Defense = defense; }

    public float GetHp() { return Hp; }
    public void SetHp(float hp) { Hp = hp; }
    public void TakeDamage(float damage) {
        damage -= this.Defense ;
        if (damage <= 0) {
            damage = (float) (Math.random() * 10);
        }
        this.Hp -= damage;
    }

    public String GetName() { return this.Name; }

    @Override
    public String toString() {
        return "Name: " + this.Name + "\n" +
                "Level: " + this.Level + "\n" +
                "Attack: " + this.Attack + "\n" +
                "Defense: " + this.Defense + "\n" +
                "Hp: " + this.Hp + "\n" +
                "Rarity: " + this.rarity + "\n";
    }

    public int calculateXP() {
        double rarityMultiplier = getRarityMultiplier();
        return (int) (100 * (1 + (Level * 0.2f)) * rarityMultiplier);
    }

}
