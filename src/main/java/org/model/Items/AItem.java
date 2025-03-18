package org.model.Items;

import org.model.Enemies.AEnemy;

import java.io.Serializable;

public abstract class AItem implements Serializable {
    public enum ItemType {
        WEAPON,
        ARMOR,
        HELMET
    }
    public enum ItemRarity {
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
        LEGENDARY
    }

    protected float getRarityMultiplier() {
        return switch (Rarity) {
            case UNCOMMON -> 1.3f;
            case RARE -> 1.7f;
            case EPIC -> 2.2f;
            case LEGENDARY -> 3.0f;
            default -> 1.0f;
        };
    }


    protected String Name;
    protected String Description;
    protected float Boost;
    protected int Level;
    protected ItemType Type;
    protected ItemRarity Rarity;

    protected float calculateBoost(float baseBoost, float rarityMultiplier) {
        return baseBoost * (1 + (Level * rarityMultiplier));
    }

    public ItemRarity getRarity() { return Rarity; }
    public ItemType getType() { return Type; }
    public String getName() { return Name; }
    public float getBoost() { return Boost; }
    public int getLevel() { return Level; }

    public void setLevel(int level) {
        Level = level;
        Boost = calculateBoost(Boost, getRarityMultiplier());
    }

    public abstract AItem Clone();
    public abstract String GetDescription();

    public void setBoost(float boost) { Boost = boost; }
    public void setName(String name) { Name = name; }
    public void setType(ItemType type) { Type = type; }
    public void setRarity(ItemRarity rarity) { Rarity = rarity; }

    @Override
    public String toString() {
        String BoostInfo = "";
        if (Type == ItemType.ARMOR) {
            BoostInfo = "Defence: " + Boost;
        }
        else if (Type == ItemType.HELMET) {
            BoostInfo = "Hp: " + Boost;
        }
        else if (Type == ItemType.WEAPON) {
            BoostInfo = "Attack: " + Boost;
        }

        return "Name: " + Name + "\n" +
                "Description: " + Description + "\n" +
                BoostInfo + "\n" +
                "Level: " + Level + "\n" +
                "Type: " + Type + "\n" +
                "Rarity: " + Rarity + "\n";
    }
}
