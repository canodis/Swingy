package org.controller;

import org.data.Database.DatabaseManager;
import org.model.Characters.AHero;

import java.util.Scanner;

public class GameController {
    private AHero hero;
    private final HeroController _heroController = new HeroController();
    private final MapController _mapController = new MapController();
    public GameController() {
        DatabaseManager.getInstance();
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
    }

    private void showMenu() {
        System.out.println("1. Select Hero");
        System.out.println("2. Create Hero");
        System.out.println("3. Delete Hero");
        System.out.println("4. Exit");

        System.out.println("Enter your choice");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
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
                System.out.println("Invalid choice");
        }
    }
}
