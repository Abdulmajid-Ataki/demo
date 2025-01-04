

package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconfig {
    // Informations de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            // Charger le driver JDBC au démarrage
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors du chargement du driver JDBC : " + e.getMessage(), e);
        }
    }

    // Méthode pour obtenir une connexion
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        if (connection.isValid(2)) { // Vérification de validité (2 secondes de timeout)
            return connection;
        } else {
            throw new SQLException("Impossible d'obtenir une connexion valide");
        }
    }

    // Méthode utilitaire pour fermer les ressources
    public static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.err.println("Erreur lors de la fermeture de la ressource : " + e.getMessage());
                }
            }
        }
    }
}
