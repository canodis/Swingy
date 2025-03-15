package org.data;

import org.controller.HeroController;
import org.model.Characters.AHero;
import org.model.Characters.Assassin;
import org.model.Characters.Mage;
import org.model.Characters.Warrior;

public class HeroFactory {
    static HeroFactory instance;

    private HeroFactory() {
    }

    public static HeroFactory getInstance() {
        if (instance == null) {
            instance = new HeroFactory();
        }
        return instance;
    }

    public AHero createHero(HeroController.HeroTypes heroType, String name) {
        if (heroType == HeroController.HeroTypes.Warrior) {
            return new Warrior(name, 1, 0, 10, 20, 100);
        }
        else if (heroType == HeroController.HeroTypes.Mage) {
            return new Mage(name, 1, 0, 25, 10, 100);
        }
        else if (heroType == HeroController.HeroTypes.Assassin) {
            return new Assassin(name, 1, 0, 30, 5, 100);
        }
        return null;
    }

    public AHero createHero(AHero hero) {
        if (hero.GetHeroClass().equals("Warrior")) {
            return new Warrior(hero.GetName(), hero.GetLevel(), hero.GetXp(), hero.GetAttack(), hero.GetDefense(), hero.GetHp());
        }
        else if (hero.GetHeroClass().equals("Mage")) {
            return new Mage(hero.GetName(), hero.GetLevel(), hero.GetXp(), hero.GetAttack(), hero.GetDefense(), hero.GetHp());
        }
        else if (hero.GetHeroClass().equals("Assassin")) {
            return new Assassin(hero.GetName(), hero.GetLevel(), hero.GetXp(), hero.GetAttack(), hero.GetDefense(), hero.GetHp());
        }
        return null;
    }
}


