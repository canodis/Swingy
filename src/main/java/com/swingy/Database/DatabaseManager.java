package com.swingy.Database;

import java.sql.*;

public class DatabaseManager {
 private static final String DB_URL = "jdbc:h2:file:~/swingy";
 private static final String DB_USER = "sa";
 private static final String DB_PASSWORD = "";

 private static Connection connection;

 public static void connect() {
  try {
   connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
   System.out.println("Database connected successfully!");

   // Test için bir basit sorgu
   Statement stmt = connection.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT 1");
   if (rs.next()) {
    System.out.println("Test query executed successfully!");
   }
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println("Failed to connect to the database!");
  }
 }


 // Tabloyu oluştur
 public static void initializeDatabase() {
  String createTableQuery = "CREATE TABLE IF NOT EXISTS heroes (" +
          "id INT AUTO_INCREMENT PRIMARY KEY, " +
          "name VARCHAR(255) NOT NULL, " +
          "hero_class VARCHAR(255) NOT NULL, " +
          "level INT NOT NULL, " +
          "experience INT NOT NULL, " +
          "attack INT NOT NULL, " +
          "defense INT NOT NULL, " +
          "hit_points INT NOT NULL" +
          ");";
  try (Statement stmt = connection.createStatement()) {
   stmt.execute(createTableQuery);
   DatabaseManager.getConnection().commit();
   System.out.println("Table 'heroes' initialized.");
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println("Failed to initialize database table!");
  }
 }

 public static Connection getConnection() {
  return connection;
 }

 // Veritabanı bağlantısını kapat
 public static void close() {
  try {
   if (connection != null) {
    connection.close();
    System.out.println("Database connection closed.");
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}
