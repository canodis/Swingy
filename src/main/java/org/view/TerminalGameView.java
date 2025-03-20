package org.view;

import org.model.Characters.AHero;
import org.model.Enemies.AEnemy;

public class TerminalGameView {
    public TerminalGameView() {

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMessage(String message, String color) {
        System.out.println(color + message + org.utils.Colors.RESET);
    }

    public void displayHeroStats(AHero hero) {
        displayMessage(hero.toString());
    }

    public void displayEnemyStats(AEnemy enemy) {
        displayMessage(enemy.toString());
    }

    public void displayBattleAttack(AHero hero, AEnemy enemy) {
        displayMessage("Hero attacked enemy for " + hero.GetAttack() + " damage Enemy HP: " + enemy.GetHp());
    }

    public void displayBattleAttack(AEnemy enemy, AHero hero) {
        displayMessage("Enemy attacked hero for " + enemy.GetAttack() + " damage Hero HP: " + hero.GetHp());
    }
}
