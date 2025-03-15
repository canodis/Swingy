package org.controller;

import org.data.Database.DatabaseManager;
import org.data.HeroFactory;
import org.model.Characters.AHero;

import java.util.Map;

public class HeroController {
    public enum HeroTypes {
        Warrior,
        Mage,
        Assassin
    }

    public HeroController() {

    }

    public AHero selectHero() {
        Map<String, AHero> heroes = DatabaseManager.getInstance().getHeroes();

        if (heroes.isEmpty()) {
            System.out.println("No heroes available, please create a hero");
            return null;
        }
        StringBuilder allHeroesNames = new StringBuilder();
        int index = 1;
        for (String heroName : heroes.keySet()) {
            allHeroesNames.append(index).append(". ").append(heroName);
            index++;
            if (index <= heroes.size()) {
                allHeroesNames.append("\n");
            }
        }
        int SelectedHeroIndex = InputController.getInstance().GetInput("Select hero: \n" + allHeroesNames, true);
        if (SelectedHeroIndex < 1 || SelectedHeroIndex > heroes.size()) {
            System.out.println("Invalid hero index");
            return null;
        }
        String heroName = (String) heroes.keySet().toArray()[SelectedHeroIndex - 1];
        AHero selectedHero = heroes.get(heroName);
        if (selectedHero == null) {
            System.out.println("Invalid hero name");
            return null;
        }
        return selectedHero;
    }

    public AHero createHero() {
        String heroName = InputController.getInstance().GetInput("Enter hero name:", false);

        StringBuilder allHeroesTypes = new StringBuilder();

        int index = 1;
        for (HeroTypes heroType : HeroTypes.values()) {
            allHeroesTypes.append(index).append(". ").append(heroType);
            index++;
            if (index <= HeroTypes.values().length) {
                allHeroesTypes.append("\n");
            }
        }
        int heroTypeIndex = InputController.getInstance().GetInput("Select hero type: \n" + allHeroesTypes, true);

        if (heroTypeIndex < 1 || heroTypeIndex > HeroTypes.values().length) {
            System.out.println("Invalid hero type");
            return null;
        }

        HeroTypes selectedHeroType = HeroTypes.values()[heroTypeIndex - 1];
        return HeroFactory.getInstance().createHero(selectedHeroType, heroName);
    }

    public void deleteHero() {
        AHero hero = selectHero();
        if (hero == null) {
            return;
        }
        DatabaseManager.getInstance().deleteHero(hero);
    }
}