package org.model.Items.Helmets;

import org.model.Items.AItem;

public class IronHelmet extends AItem {
    public IronHelmet() {
        this(1, 3.0f);
    }

    public IronHelmet(int level, float boost) {
        this.Name = "Iron Helmet";
        this.Description = "Flat iron helmet doesn't have many features";
        this.Level = level;
        this.Type = ItemType.HELMET;
        this.Rarity = ItemRarity.UNCOMMON;
        this.Boost = calculateBoost(boost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new IronHelmet(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Hp +" + this.Boost;
    }
}
