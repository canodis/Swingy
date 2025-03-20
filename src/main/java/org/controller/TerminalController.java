package org.controller;

import org.data.BattleResult;
import org.model.Characters.AHero;
import org.model.Enemies.AEnemy;
import org.utils.Colors;
import org.view.TerminalGameView;

public class TerminalController {
    TerminalGameView view;
    public TerminalController(TerminalGameView view) {
        this.view = view;
    }

    public BattleResult StartBattle(AHero hero, AEnemy enemy) {
        view.displayMessage("You encountered an enemy: " + enemy.toString());
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
                        view.displayMessage("Failed to run");
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
                    view.displayHeroStats(hero);
                    break;
                default:
                    view.displayMessage("Invalid choice", Colors.RED);
                    break;
            }
        }
    }

    private void HeroAttacksEnemy(AHero hero, AEnemy enemy) {
        enemy.TakeDamage(hero.GetAttack());
        view.displayBattleAttack(hero, enemy);
    }

    private void EnemyAttacksHero(AHero hero, AEnemy enemy) {
        hero.TakeDamage(enemy.GetAttack());
        view.displayBattleAttack(enemy, hero);
    }

    private BattleResult AutoBattleMode(AHero hero, AEnemy enemy) {
        int maxRounds = 100;
        view.displayMessage("Auto battle started your hp " + hero.GetHp() + " enemy hp " + enemy.GetHp());
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
