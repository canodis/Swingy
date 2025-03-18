package org.model.Items.Armors;

import org.model.Items.AItem;

public class LeatherArmor extends AItem {
    public LeatherArmor() {
        this(1, 1.0f);
    }

    public LeatherArmor(int level, float baseBoost) {
        this.Name = "Leather Armor";
        this.Description = "Leather armor... I hope this protects";
        this.Level = level;
        this.Type = ItemType.ARMOR;
        this.Rarity = ItemRarity.COMMON;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new LeatherArmor(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Armor +" + this.Boost;
    }
}
