package org.model.Items.Weapons;

import org.model.Items.AItem;

public class DragonSlayerSword extends AItem {
    public DragonSlayerSword() {
        this(1, 10.0f);
    }

    public DragonSlayerSword(int level, float baseBoost) {
        this.Name = "Dragon Slayer Sword";
        this.Description = "Sword made from the teeth of a dragon killed by a heroic adventurer";
        this.Level = level;
        this.Type = ItemType.WEAPON;
        this.Rarity = ItemRarity.LEGENDARY;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new DragonSlayerSword(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Power +" + this.Boost;
    }
}
