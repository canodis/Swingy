package com.swingy;

import com.swingy.Database.DatabaseManager;
import com.swingy.controller.GameController;
import com.swingy.model.Hero;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.connect();
        DatabaseManager.initializeDatabase();

        Hero hero = new Hero("Legolas", "Archer", 10, 8, 80);
        GameController gameController = new GameController();
        gameController.startGame();

        DatabaseManager.close();
    }
}
