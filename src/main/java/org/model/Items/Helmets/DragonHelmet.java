package org.model.Items.Helmets;

import org.model.Items.AItem;

public class DragonHelmet extends AItem {
    public DragonHelmet() {
        this(1, 10.0f);
    }

    public DragonHelmet(int level, float boost) {
        this.Name = "Dragon Helmet";
        this.Description = "Helmet made from the skull of a dragon slain by a heroic adventurer";
        this.Level = level;
        this.Type = ItemType.HELMET;
        this.Rarity = ItemRarity.EPIC;
        this.Boost = calculateBoost(boost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new DragonHelmet(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Hp +" + this.Boost;
    }
}
