package com.swingy.controller;

import com.swingy.model.Hero;

import java.util.Scanner;

public class GameController {
    private Hero hero;
    private final Scanner scanner;

    public GameController() {
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Swingy RPG!");
        System.out.println("1. Load Hero");
        System.out.println("2. Create Hero");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // buffer temizle

        if (choice == 1) {
            System.out.println("Enter hero name:");
        }
        else if (choice == 2) {
            createHero();
            playGame();
        } else {
            System.out.println("Goodbye!");
        }
    }

    private void createHero() {
        System.out.println("Enter hero name:");
        String name = scanner.nextLine();

        System.out.println("Choose hero class (Warrior, Mage, Rogue):");
        String heroClass = scanner.nextLine();

        int attack = 10, defense = 5, hitPoints = 30;
        if (heroClass.equalsIgnoreCase("Mage")) {
            attack = 15;
            defense = 3;
            hitPoints = 20;
        } else if (heroClass.equalsIgnoreCase("Rogue")) {
            attack = 12;
            defense = 4;
            hitPoints = 25;
        }

        this.hero = new Hero(name, heroClass, attack, defense, hitPoints);
        System.out.println("Hero created! " + hero.getName() + " the " + hero.getHeroClass());
    }

    private void playGame() {
        System.out.println("Game started with hero: " + hero.getName());
        // Oyun döngüsünü buraya ekleyeceğiz
    }
}
