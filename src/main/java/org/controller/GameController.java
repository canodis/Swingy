package org.controller;

import org.data.BattleResult;
import org.data.Database.DatabaseManager;
import org.data.Vector2;
import org.model.Characters.AHero;
import org.model.Enemies.AEnemy;
import org.model.Enemies.EnemyFactory;
import org.model.Items.AItem;
import org.model.Items.ItemFactory;
import org.utils.Colors;
import org.view.TerminalGameView;

import javax.xml.transform.Result;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private AHero hero;
    private final HeroController _heroController = new HeroController();
    private final MapController _mapController = new MapController();
    private final TerminalGameView _terminalGameView = new TerminalGameView();
    private final TerminalController _terminalController;
    boolean isGameTerminalMode = true;
    Random random = new Random();

    public GameController() {
        DatabaseManager.getInstance();
        _terminalController = new TerminalController(_terminalGameView);
        Initialize();
    }

    private void Initialize() {
        System.out.println("Welcome the game, please select one of the following options:");
        while (true) {
            showMenu();
            if (hero != null) {
                System.out.println("Welcome " + hero.GetName());
                break;
            }
        }
    }

    public void StartGame()
    {
        _mapController.CreateMap(hero);
        // game loop
        while (true) {
            _mapController.displayMap();
            String choice = InputController.getInstance().GetInput("Select movement direction: \nW. Up\nS. Down\nA. Left\nD. Right\nI. Info\nE. Exit Game", false);
            Vector2 direction = null;
            switch (choice.toLowerCase(Locale.ROOT)) {
                case "w":
                    direction = Vector2.Up;
                    break;
                case "s":
                    direction = Vector2.Down;
                    break;
                case "a":
                    direction = Vector2.Left;
                    break;
                case "d":
                    direction = Vector2.Right;
                    break;
                case "i":
                    System.out.println(hero.toString());
                    break;
                case "e":
                    DatabaseManager.getInstance().saveData();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            if (direction == null) {
                continue;
            }
            AEnemy enemy = _mapController.MoveHero(hero, direction);
            if (enemy == null) {
                continue;
            }
            BattleResult result = null;
            enemy.isVisible = true;
            _mapController.displayMap();
            if (isGameTerminalMode) {
                result = _terminalController.StartBattle(hero, enemy);
            }

            if (result == BattleResult.WIN) {
                Colors.PutColoredText("You won the battle", Colors.GREEN);
                AItem Item = null;
                if (random.nextInt(100) < 99) {
                    Item = ItemFactory.getInstance().generateItem(enemy);
                }
                int EarnedXp = enemy.calculateXP();
                if (hero.AddXp(EarnedXp)) {
                    Colors.PutColoredText("You leveled up, your new level is: " + hero.GetLevel(), Colors.YELLOW);
                } else {
                    Colors.PutColoredText("You earned " + EarnedXp + " xp, required xp for next level: " + hero.getXPRequiredForNextLevel(), Colors.YELLOW);
                }
                _mapController.SetHeroPosition(hero, enemy.position);
                EnemyFactory.getInstance().DeleteEnemy(enemy);
                if (Item != null) {
                    if (Item.getType() == AItem.ItemType.ARMOR) {
                        AItem HeroItem = hero.GetArmor();
                        if (HeroItem != null && HeroItem.getBoost() >= Item.getBoost()) {
                            Item = null;
                        }
                    }
                    else if (Item.getType() == AItem.ItemType.WEAPON) {
                        AItem HeroItem = hero.GetWeapon();
                        if (HeroItem != null && HeroItem.getBoost() >= Item.getBoost()) {
                            Item = null;
                        }
                    }
                    else if (Item.getType() == AItem.ItemType.HELMET) {
                        AItem HeroItem = hero.GetHelmet();
                        if (HeroItem != null && HeroItem.getBoost() >= Item.getBoost()) {
                            Item = null;
                        }
                    }
                    if (Item != null) {
                        if (Item.getRarity() == AItem.ItemRarity.LEGENDARY) {
                            Colors.PutColoredText("\uD83D\uDD25 You found an LEGENDARY item: " + Item.toString() + " \uD83D\uDD25", Colors.GREEN);
                        }
                        else {
                            Colors.PutColoredText("You found an item: " + Item.toString(), Colors.GREEN);
                        }
                        String equip = InputController.getInstance().GetInput("Do you want to equip it? (y) Yes, (n) No", false);
                        if (equip.equalsIgnoreCase("y")) {
                            hero.EquipItem(Item);
                        }
                    }
                }

            } else if (result == BattleResult.LOSE) {
                Colors.PutColoredText("You lost the battle, Game Over", Colors.RED);
//                DatabaseManager.getInstance().deleteHero(hero);
                break;
            } else if (result == BattleResult.RUN) {
                Colors.PutColoredText("You ran away", Colors.PURPLE);
            }
            hero.RestoreHp();
        }
    }

    private void showMenu() {
        System.out.println("1. Select Hero");
        System.out.println("2. Create Hero");
        System.out.println("3. Delete Hero");
        System.out.println("4. Exit");

        int choice = InputController.getInstance().GetInput("Enter your choice", true);
        switch (choice) {
            case 1:
                hero = _heroController.selectHero();
                break;
            case 2:
                DatabaseManager.getInstance().addHero(_heroController.createHero());
                break;
            case 3:
                _heroController.deleteHero();
                break;
            case 4:
                DatabaseManager.getInstance().saveData();
                System.exit(0);
                break;
            default:
                Colors.PutColoredText("Invalid choice", Colors.RED);
        }
    }
}
