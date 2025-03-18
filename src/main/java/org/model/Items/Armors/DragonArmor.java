package org.model.Items.Armors;

import org.model.Items.AItem;

public class DragonArmor extends AItem {
    public DragonArmor() {
        this(1, 10.0f);
    }

    public DragonArmor(int level, float baseBoost) {
        this.Name = "Dragon Armor";
        this.Description = "Armor made from the scales of a dragon slain by a heroic adventurer";
        this.Level = level;
        this.Type = ItemType.ARMOR;
        this.Rarity = ItemRarity.EPIC;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new DragonArmor(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Armor +" + this.Boost;
    }
}
