package dao;

import metier.User;

import java.sql.*;
import java.util.ArrayList;


public class UserDao implements IUser {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String login = "root";
    private static final String PASSWORD = "";
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private  Connection connection ;


    public UserDao() throws SQLException, ClassNotFoundException {
        // Charger le driver
        Class.forName(driver);
        // Établir la connexion
        this.connection = DriverManager.getConnection(URL, login, PASSWORD);
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO User (id, nom, prenom, email,Telephone,adresse,date_inscription,role) VALUES (?, ?, ?, ?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getIdUser());
            preparedStatement.setString(2, user.getNomUser());
            preparedStatement.setString(3, user.getPrenomUser());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5,user.getTelephone());
            preparedStatement.setString(6,user.getAdresse());
            preparedStatement.setDate(7,new java.sql.Date(user.getDateInscription().getTime()));
            preparedStatement.setString(8,user.getRole().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'ajout de ce utilisateur.Veuillez essayer plus tard");
        }
    }

    @Override
    public User getUser(int idUser) {
        String sql = "SELECT * FROM User WHERE id = ?";
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setIdUser(resultSet.getInt("id"));
                user.setNomUser(resultSet.getString("nom"));
                user.setPrenomUser(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("numero-telephone"));
                user.setAdresse(resultSet.getString("l'adresse"));
                user.setDateInscription(resultSet.getDate("date_inscription"));
                user.setRole(User.Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println("erreur au niveau de la recherche de ce utilisateur.Veuillez essayer plus tard");
        }
        return user;    }

    @Override
    public ArrayList<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        ArrayList<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id"));
                user.setNomUser(resultSet.getString("nom"));
                user.setPrenomUser(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("numero-telephone"));
                user.setAdresse(resultSet.getString("l'adresse"));
                user.setDateInscription(resultSet.getDate("date-inscription"));
                user.setRole(User.Role.valueOf(resultSet.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
        return users;    }

    @Override
    public void deleteUser(int idUser) {
        String sql = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
    }
    public void updateUser(User user) {
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("Données utilisateur invalides");
        }

        String sql = "UPDATE User SET nom = ?, prenom = ?, email = ?, telephone = ?, adresse = ?, role = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getNomUser());
            preparedStatement.setString(2, user.getPrenomUser());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getTelephone());
            preparedStatement.setString(5, user.getAdresse());
            preparedStatement.setString(6, user.getRole().toString());
            preparedStatement.setInt(7, user.getIdUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
    }


}


