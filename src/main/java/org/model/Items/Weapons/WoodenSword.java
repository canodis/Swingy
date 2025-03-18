package org.model.Items.Weapons;

import org.model.Items.AItem;

public class WoodenSword extends AItem {
    public WoodenSword() {
        this(1, 1.0f);
    }

    public WoodenSword(int level, float baseBoost) {
        this.Name = "Wooden Sword";
        this.Description = "Wooden sword... it doesn't inspire confidence";
        this.Level = level;
        this.Type = ItemType.WEAPON;
        this.Rarity = ItemRarity.COMMON;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new WoodenSword(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Power +" + this.Boost;
    }
}
