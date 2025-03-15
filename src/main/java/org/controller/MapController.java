package org.controller;

import org.data.Vector2;
import org.model.Characters.AHero;
import org.model.Enemies.AEnemy;
import org.model.Enemies.EnemyFactory;
import org.model.GameObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MapController {
    Map<Vector2, GameObject> map;
    int MapSize;

    public MapController() {
        map = new HashMap<>();
    }

    // Creation formula (level-1)*5+10 - (level%2).
    void CreateMap(AHero hero) {
        MapSize = (hero.GetLevel() - 1) * 5 + 10 - (hero.GetLevel() % 2);
        Vector2 CenterPosition = new Vector2(MapSize / 2, MapSize / 2);

        hero.position.set(CenterPosition);
        map.put(hero.position, hero);

        for (int y = 0; y < MapSize; y++) {
            for (int x = 0; x < MapSize; x++) {
                Vector2 Position = new Vector2(x, y);
                if (map.containsKey(Position)) {
                    continue;
                }
                map.put(Position, null);
            }
        }
        SpawnEnemies(hero.GetLevel());
        displayMap();
    }

    public void displayMap() {
        for (int y = 0; y < MapSize; y++) {
            for (int x = 0; x < MapSize; x++) {
                PrintGameObjectToTerminal(map.get(new Vector2(x, y)));
            }
            System.out.println();
        }
    }

    private void SpawnEnemies(int heroLevel) {
        int totalCells = MapSize * MapSize;
        int enemyCount = (int) (totalCells * 0.25);
        int spawnedEnemies = 0;

        Random random = new Random();

        while (spawnedEnemies < enemyCount) {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(MapSize);
            Vector2 position = new Vector2(x, y);

            if (map.get(position) == null) {
                AEnemy enemy = EnemyFactory.getInstance().SpawnEnemy(CalculateEnemyLevel(heroLevel));
                map.put(position, enemy);
                spawnedEnemies++;
            }
        }
    }

    private int CalculateEnemyLevel(int heroLevel) {
        return ThreadLocalRandom.current().nextInt(Math.max(1, heroLevel - 2),heroLevel + 1);
    }

    private void PrintGameObjectToTerminal(GameObject gameObject) {
        if (gameObject == null || !gameObject.isVisible) {
            System.out.print(' ');
            return;
        }

        System.out.print(gameObject.Icon);
        if (gameObject.Icon.length() == 1) {
            System.out.print(' ');
        }
//        if (gameObject instanceof Warrior) {
//            AHero hero = (Warrior) gameObject;
//            System.out.print(hero.GetName());
//            return;
//        }
    }
}
