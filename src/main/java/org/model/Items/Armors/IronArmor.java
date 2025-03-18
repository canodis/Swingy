package org.model.Items.Armors;

import org.model.Items.AItem;

public class IronArmor extends AItem {
    public IronArmor() {
        this(1, 3.0f);
    }

    public IronArmor(int level, float baseBoost) {
        this.Name = "Iron Armor";
        this.Description = "Flat iron armor doesn't have many features.";
        this.Level = level;
        this.Type = ItemType.ARMOR;
        this.Rarity = ItemRarity.UNCOMMON;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new IronArmor(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Armor +" + this.Boost;
    }
}
