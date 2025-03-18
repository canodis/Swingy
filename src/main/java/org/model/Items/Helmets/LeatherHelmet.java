package org.model.Items.Helmets;

import org.model.Items.AItem;

import java.util.logging.Level;

public class LeatherHelmet extends AItem {
    public LeatherHelmet() {
        this(1, 1.0f);
    }

    public LeatherHelmet(int level, float boost) {
        this.Name = "Leather Helmet";
        this.Description = "Leather helmet... hope your head stays in place";
        this.Level = level;
        this.Type = ItemType.HELMET;
        this.Rarity = ItemRarity.COMMON;
        this.Boost = calculateBoost(boost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new LeatherHelmet(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Hp +" + this.Boost;
    }
}
