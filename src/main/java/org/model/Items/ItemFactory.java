package org.model.Items;

import org.model.Enemies.*;
import org.model.Items.Armors.*;
import org.model.Items.Weapons.*;
import org.model.Items.Helmets.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    private static final Random random = new Random();
    private static ItemFactory instance = null;

    private ItemFactory() {
        if (instance == null) {
            instance = this;
        }
    }

    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }
        return instance;
    }

    private static final List<AItem> ItemPrototypes = List.of(
            new LeatherArmor(), new IronArmor(), new GoldenArmor(), new DragonArmor(), new AnimeArmor(),
            new WoodenSword(), new IronSword(), new GoldenSword(), new DragonSlayerSword(), new AnimeSword(),
            new LeatherHelmet(), new IronHelmet(), new GoldenHelmet(), new DragonHelmet(), new AnimeHelmet()

    );

    public AItem generateItem(AEnemy enemy) {
        AItem.ItemRarity itemRarity = selectRarity(enemy);

        AItem.ItemType type = selectType();

        int level = calculateItemLevel(enemy.GetLevel());

        return createItem(type, itemRarity, level, enemy);
    }

    private AItem.ItemRarity selectRarity(AEnemy enemy) {
        double[] baseWeights = {50, 30, 15, 4, 1};

        float rarityMultiplier = enemy.getRarityMultiplier();
        for (int i = 0; i < baseWeights.length; i++) {
            baseWeights[i] *= Math.pow(rarityMultiplier, i);
        }

        double totalWeight = Arrays.stream(baseWeights).sum();
        double randomValue = random.nextDouble() * totalWeight;

        for (int i = 0; i < baseWeights.length; i++) {
            randomValue -= baseWeights[i];
            if (randomValue <= 0) {
                return AItem.ItemRarity.values()[i];
            }
        }
        return AItem.ItemRarity.COMMON;
    }

    private int calculateItemLevel(int enemyLevel) {
        int level = enemyLevel + random.nextInt(5) - 2;

        return Math.max(1, level);
    }

    private AItem.ItemType selectType() {
        double rand = random.nextDouble();
        if (rand < 0.40) return AItem.ItemType.WEAPON;
        else if (rand < 0.75) return AItem.ItemType.ARMOR;
        else return AItem.ItemType.HELMET;
    }

    private AItem createItem(AItem.ItemType type, AItem.ItemRarity itemRarity, int level, AEnemy enemy) {
        float enemyRarityBoost = enemy.getRarityMultiplier();

        float baseBoost = switch (itemRarity) {
            case COMMON -> 1;
            case UNCOMMON -> 3;
            case RARE -> 5;
            case EPIC -> 10;
            case LEGENDARY -> 20;
        };

        float scaledBoost = baseBoost * (1 + (level * 0.1f)) * enemyRarityBoost;

        List<AItem> items = new ArrayList<>();
        for (AItem item : ItemPrototypes) {
            if (item.getType() == type && item.getRarity() == itemRarity) {
                items.add(item);
            }
        }

        AItem item = items.get(random.nextInt(items.size()));
        if (item == null) {
            return null;
        }

        AItem newItem = item.Clone();
        newItem.setLevel(level);
        newItem.setBoost(scaledBoost);

        return newItem;
    }
}