package com.swingy.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swingy.model.Hero;

public class HeroRepository {

    public static void saveHero(Hero hero) {
        String insertQuery = "INSERT INTO heroes (name, hero_class, level, experience, attack, defense, hit_points) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery)) {
            pstmt.setString(1, hero.getName());
            pstmt.setString(2, hero.getHeroClass());
            pstmt.setInt(3, hero.getLevel());
            pstmt.setInt(4, hero.getExperience());
            pstmt.setInt(5, hero.getAttack());
            pstmt.setInt(6, hero.getDefense());
            pstmt.setInt(7, hero.getHitPoints());
            pstmt.executeUpdate();
            DatabaseManager.getConnection().commit();
            System.out.println("Hero saved: " + hero.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<>();
        String selectQuery = "SELECT * FROM heroes;";
        try (Statement stmt = DatabaseManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                Hero hero = new Hero(
                        rs.getString("name"),
                        rs.getString("hero_class"),
                        rs.getInt("attack"),
                        rs.getInt("defense"),
                        rs.getInt("hit_points")
                );
                hero.gainExperience(rs.getInt("experience") - hero.getExperience()); // Level ve XP güncellemesi
                heroes.add(hero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}
