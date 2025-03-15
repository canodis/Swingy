package org.model.Characters;

import org.data.Vector2;
import org.model.GameObject;

import javax.validation.constraints.*;
import java.io.Serializable;

public abstract class AHero extends GameObject implements Serializable {
    @NotNull(message = "Name cannot be empty!")
    @Size(min = 3, max = 20, message = "Hero name can min 3 characters and max 20 characters")
    protected String Name;

    @NotNull(message = "Hero class cannot be empty!")
    protected String Class;

    @Min(value = 0, message = "Hero level cannot be negative!")
    protected int Level;

    @Min(value = 0, message = "Hero xp cannot be negative!")
    protected float Xp;

    @Min(value = 1, message = "Hero attack cannot less than 1")
    protected float Attack;

    @Min(value = 0, message = "Hero defence cannot be negative")
    protected float Defense;

    @Min(value = 0, message = "Hero hp cannot be negative")
    protected float Hp;

    protected AHero(String name, String heroClass, int level, float xp, float attack, float defense, float hp) {
        this.Name = name;
        this.Class = heroClass;
        this.Level = level;
        this.Xp = xp;
        this.Attack = attack;
        this.Defense = defense;
        this.position = new Vector2();
        this.Icon = "\uD83D\uDC79";
        this.isVisible = true;
        this.Hp = hp;
    }

    public String GetName() { return Name; }
    public void SetName(String name) { Name = name; }

    public String GetHeroClass() { return Class; }
    public void SetHeroClass(String heroClass) { Class = heroClass; }

    public int GetLevel() { return Level; }
    public void SetLevel(int level) { Level = level; }

    public float GetXp() { return Xp; }
    // level up formula = level ∗ 1000 + ((level − 1) pow 2) ∗ 450.
    public void AddXp(float xp) {
        this.Xp += xp;
        boolean levelUp = this.Level * 1000 + ((this.Level - 1) * (this.Level - 1)) * 450 <= this.Xp;
        if (levelUp) {
            applyLevelUpStats();
        }
    }
    protected abstract void applyLevelUpStats();

    public float GetAttack() { return Attack; }
    public void SetAttack(float attack) { Attack = attack; }

    public float GetDefense() { return Defense; }
    public void SetDefense(float defense) { Defense = defense; }

    public float GetHp() { return Hp; }
    public void SetHp(float hp) { Hp = hp; }
}