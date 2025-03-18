package org.model.Items.Armors;

import org.model.Items.AItem;

public class GoldenArmor extends AItem {
    public GoldenArmor() {
        this(1, 5.0f);
    }

    public GoldenArmor(int level, float baseBoost) {
        this.Name = "Golden Armor";
        this.Description = "Pure gold armor forged by a master blacksmith";
        this.Level = level;
        this.Type = ItemType.ARMOR;
        this.Rarity = ItemRarity.RARE;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new GoldenArmor(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Armor +" + this.Boost;
    }
}
