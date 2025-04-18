package org.controller;

import org.data.Vector2;
import org.model.Characters.AHero;
import org.model.EmptySpace;
import org.model.Enemies.AEnemy;
import org.model.Enemies.EnemyFactory;
import org.model.GameObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MapController {
    Random rand = new Random();
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
                GameObject emptySpace = new EmptySpace();
                emptySpace.position.set(Position);
                map.put(Position, emptySpace);
            }
        }
        SpawnEnemies(hero.GetLevel());
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
        int enemyCount = (int) (totalCells * 0.35);
        int spawnedEnemies = 0;

        Random random = new Random();

        while (spawnedEnemies < enemyCount) {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(MapSize);
            Vector2 position = new Vector2(x, y);

            if (map.get(position) == null || map.get(position) instanceof EmptySpace) {
                AEnemy enemy = EnemyFactory.getInstance().SpawnEnemy(CalculateEnemyLevel(heroLevel));
                map.put(position, enemy);
                enemy.position.set(position);
                spawnedEnemies++;
            }
        }
    }

    private int CalculateEnemyLevel(int heroLevel) {
        int randomLevel = heroLevel + rand.nextInt(3) - 1;
        return Math.max(randomLevel, 1);
    }

    private void PrintGameObjectToTerminal(GameObject gameObject) {
        if (gameObject == null) {
            System.out.print(". ");
            return;
        }
        else if (!gameObject.isVisible) {
            System.out.print("# ");
            return;
        }

        System.out.print(gameObject.Icon);
        if (gameObject.Icon.length() == 1) {
            System.out.print(' ');
        }
    }

    public AEnemy MoveHero(AHero hero, Vector2 direction) {
        Vector2 newPosition = hero.position.add(direction);
        if (newPosition.x < 0 || newPosition.x >= MapSize || newPosition.y < 0 || newPosition.y >= MapSize) {
            System.out.println("Invalid movement");
            return null;
        }

        GameObject gameObject = map.get(newPosition);
        if (gameObject != null && !(gameObject instanceof AEnemy)) {
            VisibleRandomGameObjects(newPosition);
        }

        if (gameObject == null || gameObject instanceof EmptySpace) {
            map.put(hero.position, null);
            hero.position.set(newPosition);
            map.put(newPosition, hero);
            return null;
        }
        if (gameObject instanceof AEnemy) {
            return (AEnemy) gameObject;
        }
        return null;
    }

    public void SetHeroPosition(AHero hero, Vector2 position) {
        map.put(hero.position, null);
        hero.position.set(position);
        map.put(position, hero);
    }

    private void VisibleRandomGameObjects(Vector2 position) {
        Vector2[] directions = new Vector2[] {Vector2.Up, Vector2.Down, Vector2.Left, Vector2.Right};
        for (Vector2 direction : directions) {
            Vector2 newPosition = position.add(direction);
            if (newPosition.x < 0 || newPosition.x >= MapSize || newPosition.y < 0 || newPosition.y >= MapSize) {
                continue;
            }
            GameObject gameObject = map.get(newPosition);
            if (gameObject != null) {
                if (ThreadLocalRandom.current().nextInt(0, 100) < 35) {
                    gameObject.isVisible = true;
                }
            }
        }
    }
}
