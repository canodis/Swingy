package org.model.Enemies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnemyFactory {
    static EnemyFactory instance = null;
    public List<AEnemy> enemies;
    private final Random random = new Random();

    private EnemyFactory() {
        if (instance == null) {
            enemies = new ArrayList<>();
            instance = this;
        }
    }

    public static EnemyFactory getInstance() {
        if (instance == null) {
            instance = new EnemyFactory();
        }
        return instance;
    }

    private static final List<AEnemy> enemyPrototypes = List.of(
            new Medusa(),
            new Dragon(),
            new Goblin(),
            new Troll(),
            new Zombie()
    );

    public AEnemy SpawnEnemy(int level) {
        AEnemy enemy = getRandomEnemy(level);
        if (enemy == null) {
            return null;
        }
        enemies.add(enemy);
        return enemy;
    }

    private AEnemy getRandomEnemy(int level)
    {
        double totalWeight = 0;
        for (AEnemy enemy : enemyPrototypes) {
            totalWeight += enemy.getWeight();
        }

        double randomNumber = random.nextDouble() * totalWeight;

        for (AEnemy enemy : enemyPrototypes) {
            randomNumber -= enemy.getWeight();
            if (randomNumber <= 0) {
                return enemy.Clone(level);
            }
        }
        return null;
    }

    public void DeleteEnemy(AEnemy enemy) {
        enemies.remove(enemy);
    }
}

