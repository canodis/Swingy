package org.model.Items.Helmets;

import org.model.Items.AItem;

public class AnimeHelmet extends AItem {
    public AnimeHelmet() {
        this(1, 20.0f);

    }

    public AnimeHelmet(int level, float baseBoost) {
        this.Name = "Anime Helmet";
        this.Description = "Special helmet brought from another world, enemies will hesitate to hit you because you look so cute wearing it";
        this.Level = level;
        this.Type = ItemType.HELMET;
        this.Rarity = ItemRarity.EPIC;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new AnimeHelmet(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Hp +" + this.Boost;
    }
}
