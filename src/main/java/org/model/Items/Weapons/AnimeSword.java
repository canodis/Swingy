package org.model.Items.Weapons;

import org.model.Items.AItem;

public class AnimeSword extends AItem {
    public AnimeSword() {
        this(1, 1.0f);
    }

    public AnimeSword(int level, float baseBoost) {
        this.Name = "Anime Sword";
        this.Description = "The sword of a swordsman named Zoro, who came to this world from a different world, is cursed and very powerful, be careful when using it!";
        this.Level = level;
        this.Type = ItemType.WEAPON;
        this.Rarity = ItemRarity.LEGENDARY;
        this.Boost = calculateBoost(baseBoost, getRarityMultiplier());
    }

    @Override
    public AItem Clone() {
        return new AnimeSword(Level, Boost);
    }

    @Override
    public String GetDescription() {
        return this.Description + "\n Power +" + this.Boost;
    }
}
