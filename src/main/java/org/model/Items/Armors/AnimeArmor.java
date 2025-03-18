package org.model.Items.Armors;

import org.model.Items.AItem;

public class AnimeArmor extends AItem {
    public AnimeArmor() {
        this(1, 20.0f);
    }

    public AnimeArmor(int level, float baseBoost) {
        this.Name = "Anime Armor";
        this.Description = "Magical armor brought to this world by a teenager who managed to travel to the anime world";
        this.Level = level;
        this.Type = ItemType.ARMOR;
        this.Rarity = ItemRarity.LEGENDARY;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new AnimeArmor(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Armor +" + this.Boost;
    }
}