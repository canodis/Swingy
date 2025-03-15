package org.model.Enemies;

import org.data.Vector2;
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
    protected int Attack;
    protected int Defense;
    protected int Hp;
    protected Rarity rarity;

    protected AEnemy(int level, int attack, int defense, int hp) {
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
            case UNCOMMON -> 1.3f;
            case RARE -> 1.7f;
            case EPIC -> 2.2f;
            case LEGENDARY -> 3.0f;
            default -> 1.0f;
        };
    }

    protected int scaleStat(int baseValue, int level) {
        return (int) (baseValue * Math.pow(1.15, level - 1) * getRarityMultiplier());
    }

    public abstract AEnemy Clone(int Level);

    public int GetLevel() { return Level; }
    public void SetLevel(int level) { Level = level; }

    public int GetAttack() { return Attack; }
    public void SetAttack(int attack) { Attack = attack; }

    public int GetDefense() { return Defense; }
    public void SetDefense(int defense) { Defense = defense; }

    public int GetHp() { return Hp; }
    public void SetHp(int hp) { Hp = hp; }
}
