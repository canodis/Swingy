package org.model.Items.Helmets;

import org.model.Items.AItem;

import java.util.logging.Level;

public class GoldenHelmet extends AItem {
    public GoldenHelmet() {
        this(1, 5.0f);
    }

    public GoldenHelmet(int level, float boost) {
        this.Name = "Golden Helmet";
        this.Description = "Helmet forged with pure gold by a master blacksmith";
        this.Level = level;
        this.Type = ItemType.HELMET;
        this.Rarity = ItemRarity.RARE;
        this.Boost = calculateBoost(boost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new GoldenHelmet(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Hp +" + this.Boost;
    }
}
