package test;

import config.Databaseconfig;

import java.sql.Connection;
import java.sql.SQLException;

public class connexion_BD {
    public static void main(String[] args) {
        System.out.println("Tentative de connexion à la base de données...");
        try (Connection connection = Databaseconfig.getConnection()) {
            if (connection != null) {
                System.out.println("Connexion réussie !");
            } else {
                System.out.println("Échec de la connexion !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur : Impossible d'établir une connexion avec la base de données.");
            throw new RuntimeException("Erreur de connexion à la base de données", e);


        }
    }
}

