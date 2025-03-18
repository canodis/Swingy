package org.model.Items.Weapons;

import org.model.Items.AItem;

public class GoldenSword extends AItem {
    public GoldenSword() {
        this(1, 1.0f);
    }

    public GoldenSword(int level, float baseBoost) {
        this.Name = "Golden Sword";
        this.Description = "Sword of pure gold forged by a master blacksmith";
        this.Level = level;
        this.Type = ItemType.WEAPON;
        this.Rarity = ItemRarity.RARE;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new GoldenSword(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Power +" + this.Boost;
    }
}
