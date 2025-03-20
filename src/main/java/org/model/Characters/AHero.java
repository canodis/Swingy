package org.model.Characters;

import org.data.Vector2;
import org.model.GameObject;
import org.model.Items.AItem;

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
    protected int Xp;

    @Min(value = 1, message = "Hero attack cannot less than 1")
    protected float Attack;

    @Min(value = 0, message = "Hero defence cannot be negative")
    protected float Defense;

    @Min(value = 0, message = "Hero hp cannot be negative")
    protected float MaxHp;

    float Hp;

    @Min(value = 0, message = "Hero run change cannot be negative") @Max(value = 100, message = "Hero run change cannot be more than 100")
    protected float RunChange;

    AItem weapon;
    AItem armor;
    AItem helmet;

    protected AHero(String name, String heroClass, int level, int xp, float attack, float defense, float hp) {
        this.Name = name;
        this.Class = heroClass;
        this.Level = level;
        this.Xp = xp;
        this.Attack = attack;
        this.Defense = defense;
        this.position = new Vector2();
        this.Icon = "H";
        this.isVisible = true;
        this.MaxHp = hp;
        Hp = this.MaxHp;
    }

    public String GetName() { return Name; }
    public void SetName(String name) { Name = name; }

    public String GetHeroClass() { return Class; }
    public void SetHeroClass(String heroClass) { Class = heroClass; }

    public int GetLevel() { return Level; }
    public void SetLevel(int level) { Level = level; }

    public int GetXp() { return Xp; }

    protected abstract void applyLevelUpStats();

    public float GetAttack() {
        float attack = this.Attack;
        if (weapon != null) {
            attack += weapon.getBoost();
        }
        // randomize attack by 10%
        attack += (float) ((Math.random() * 0.2 - 0.1) * attack);
        return attack;
    }

    public void SetAttack(float attack) { Attack = attack; }

    public float GetDefense() { return Defense; }
    public void SetDefense(float defense) { Defense = defense; }

    public float GetHp() { return Hp; }
    public void SetHp(float hp) { Hp = hp; }
    public float GetRunChange() { return RunChange; }
    public void RestoreHp() { Hp = MaxHp; }

    public void TakeDamage(float damage) {
        float Defense = this.Defense;
        if (armor != null) {
            Defense += armor.getBoost();
        }
        damage -= Defense;
        if (damage <= 0) {
            damage = (float) (Math.random() * 10);
        }
        this.Hp -= damage;
    }

    public AItem GetArmor() { return armor; }
    public void SetArmor(AItem armor) {
        this.armor = armor;
    }

    public AItem GetHelmet() { return helmet; }
    public void SetHelmet(AItem helmet) {
        this.helmet = helmet;
        this.MaxHp += helmet.getBoost();
        this.Hp = this.MaxHp;
    }

    public AItem GetWeapon() { return weapon; }
    public void SetWeapon(AItem weapon) { this.weapon = weapon; }

    public void EquipItem(AItem item) {
        if (item.getType() == AItem.ItemType.ARMOR) {
            SetArmor(item);
        } else if (item.getType() == AItem.ItemType.HELMET) {
            SetHelmet(item);
        } else if (item.getType() == AItem.ItemType.WEAPON) {
            SetWeapon(item);
        }
    }

    @Override
    public String toString() {
        String weaponName = weapon != null ? weapon.getName() + ", Atk :" + weapon.getBoost() : "None";
        String armorName = armor != null ? armor.getName() + ", Def +" + armor.getBoost() : "None";
        String helmetName = helmet != null ? helmet.getName() + ", Hp +" + helmet.getBoost() : "None";
        return "Name='" + Name + '\'' + ", Class='" + Class + '\'' +  ", Level=" + Level +  ", Attack=" + Attack +
                ", Defense=" + Defense +  ", Hp=" + Hp +  ", RunChange= %" + RunChange + ", Xp=" + Xp +
                ", Weapon=" + weaponName + ", Armor=" + armorName + ", Helmet=" + helmetName;
    }

    // level up formula = level ∗ 1000 + ((level − 1) pow 2) ∗ 450.
    public boolean AddXp(int xp) {
        this.Xp += xp;
        int nextLevelXp = (this.Level * 1000 + ((this.Level - 1) * (this.Level - 1)) * 450);
        if (nextLevelXp <= this.Xp) {
            this.Xp -= nextLevelXp;
            this.Level++;
            applyLevelUpStats();
            this.MaxHp += helmet != null ? helmet.getBoost() : 0;
            this.Hp = this.MaxHp;
            return true;
        }
        return false;
    }

    public int getXPRequiredForNextLevel() {
        return (this.Level * 1000 + ((this.Level - 1) * (this.Level - 1)) * 450) - this.Xp;
    }
}