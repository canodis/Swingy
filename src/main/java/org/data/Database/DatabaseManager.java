package org.data.Database;

import org.data.HeroFactory;
import org.model.Characters.AHero;
import org.model.Items.AItem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager implements Serializable {
    private static DatabaseManager instance;
    private final Map<String, AHero> heroes = new HashMap<>();
    private final Map<String, AItem> items = new HashMap<>();

    private final String ItemsFile = "Items.dat";
    private final String HeroesFile = "Heroes.dat";


    private DatabaseManager() {
        loadData();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HeroesFile))) {
            Map<String, AHero> loadedHeroes = (Map<String, AHero>) ois.readObject();
//            for (AHero hero : loadedHeroes.values()) {
//                heroes.put(hero.GetName(), HeroFactory.getInstance().createHero(hero));
//            }
            heroes.putAll(loadedHeroes);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hero save file not found.");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ItemsFile))) {
            Map<String, AItem> loadedItems = (Map<String, AItem>) ois.readObject();
            items.putAll(loadedItems);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Item save file not found.");
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HeroesFile))) {
            oos.writeObject(heroes);
        } catch (IOException e) {
            System.out.println("Heroes cannot be saved: " + e.getMessage());
        }

        // Items kaydet
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ItemsFile))) {
            oos.writeObject(items);
        } catch (IOException e) {
            System.out.println("Items cannot be saved: " + e.getMessage());
        }
    }

    public void deleteHero(AHero hero) {
        if (hero == null) {
            return;
        }
        heroes.remove(hero.GetName());
        saveData();
    }

    public void addHero(AHero hero) {
        if (hero == null) {
            return;
        }
        heroes.put(hero.GetName(), hero);
        saveData();
    }

    public void addItem(AItem item) {
        if (item == null) {
            return;
        }
        items.put(item.getName(), item);
        saveData();
    }

    public Map<String, AHero> getHeroes() {
        return heroes;
    }
}