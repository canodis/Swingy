package org.controller;

import org.data.BattleResult;
import org.model.Characters.AHero;
import org.model.Enemies.AEnemy;

public class TerminalController {
    public TerminalController() {
    }

    public BattleResult StartBattle(AHero hero, AEnemy enemy) {
        System.out.println("You encountered an enemy: " + enemy.toString());
        float runChance = hero.GetRunChange();
        while (true) {
            int choice = InputController.getInstance().GetInput("Enter choice : (1) Attack, (2) Run, (3) Auto battle mode, (4) Check your stats", true);
            switch (choice) {
                case 1:
                    HeroAttacksEnemy(hero, enemy);
                    if (enemy.GetHp() <= 0) {
                        return BattleResult.WIN;
                    }
                    EnemyAttacksHero(hero, enemy);
                    if (hero.GetHp() <= 0) {
                        return BattleResult.LOSE;
                    }
                    break;
                case 2:
                    if (runChance > Math.random() * 100) {
                        return BattleResult.RUN;
                    } else {
                        System.out.println("Failed to run");
                        runChance /= 2;
                        EnemyAttacksHero(hero, enemy);
                        if (hero.GetHp() <= 0) {
                            return BattleResult.LOSE;
                        }
                    }
                    break;
                case 3:
                    return AutoBattleMode(hero, enemy);
                case 4:
                    System.out.println(hero.toString());
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private void HeroAttacksEnemy(AHero hero, AEnemy enemy) {
        enemy.TakeDamage(hero.GetAttack());
        System.out.println("Hero attacked enemy for " + hero.GetAttack() + " damage Enemy HP: " + enemy.GetHp());
    }

    private void EnemyAttacksHero(AHero hero, AEnemy enemy) {
        hero.TakeDamage(enemy.GetAttack());
        System.out.println("Enemy attacked hero for " + enemy.GetAttack() + " damage Hero HP: " + hero.GetHp());
    }

    private BattleResult AutoBattleMode(AHero hero, AEnemy enemy) {
        int maxRounds = 100;
        System.out.println("Auto battle started your hp " + hero.GetHp() + " enemy hp " + enemy.GetHp());
        while (true) {
            HeroAttacksEnemy(hero, enemy);
            if (enemy.GetHp() <= 0) {
                return BattleResult.WIN;
            }
            EnemyAttacksHero(hero, enemy);
            if (hero.GetHp() <= 0) {
                return BattleResult.LOSE;
            }
            maxRounds--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (maxRounds <= 0) {
                if (hero.GetHp() > enemy.GetHp()) {
                    return BattleResult.WIN;
                } else {
                    return BattleResult.LOSE;
                }
            }
        }
    }
}
