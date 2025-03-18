package org.model.Items.Weapons;

import org.model.Items.AItem;

public class IronSword extends AItem {
    public IronSword() {
        this(1, 3.0f);
    }
    public IronSword(int level, float baseBoost) {
        this.Name = "Iron Sword";
        this.Description = "A straight iron sword will do the job";
        this.Level = level;
        this.Type = ItemType.WEAPON;
        this.Rarity = ItemRarity.COMMON;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new IronSword(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Power +" + this.Boost;
    }
}
