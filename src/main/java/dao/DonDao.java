package dao;

import metier.Don;

import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
public class DonDao implements IDon {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String login= "root";
    private static final String PASSWORD = "";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    private Connection connection;

    public DonDao() throws SQLException, ClassNotFoundException {

        Class.forName(driver);
        this.connection = DriverManager.getConnection(URL, login, PASSWORD);
    }


    public void addDonation(Don donation) {
        String sql = "INSERT INTO Donation (idDon, Montant, typeDon, dateDon, idUser, idProjet) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, donation.getIdDon());
            preparedStatement.setDouble(2, donation.getMontant());
            preparedStatement.setString(3, donation.getTypeDon());
            preparedStatement.setDate(4, new java.sql.Date(donation.getDateDon().getTime()));
            preparedStatement.setInt(5, donation.getIdUser());
            preparedStatement.setInt(6, donation.getIdProjet());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
    }

    // Récupérer un don par ID
    public Don getDon(int id) {
        String sql = "SELECT * FROM Donation WHERE idDon = ?";
        Don donation = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                donation.setIdDon(resultSet.getInt("idDon"));
                donation.setMontant(resultSet.getDouble("Montant"));
                donation.setTypeDon(resultSet.getString("typeDon"));
                donation.setDateDon(resultSet.getDate("dateDon"));
                donation.setIdUser(resultSet.getInt("idUser"));
                donation.setIdProjet(resultSet.getInt("idProjet"));
            }
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
        return donation;
    }

    // Récupérer tous les dons
    public ArrayList<Don> getAllDonations()  {

        String sql = "SELECT * FROM don";
        ArrayList<Don> dons = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Don donation = new Don();
                donation.setIdDon(resultSet.getInt("idDon"));
                donation.setMontant(resultSet.getDouble("Montant"));
                donation.setTypeDon(resultSet.getString("typeDon"));
                donation.setDateDon(resultSet.getDate("dateDon"));
                donation.setIdUser(resultSet.getInt("idUser"));
                donation.setIdProjet(resultSet.getInt("idProjet"));
                dons.add(donation);
            }
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
        System.out.println("Nombre de dons récupérés : " + dons.size());
        return dons;
    }

    // Supprimer un don par ID
    public void deleteDonation(int id) {
        String sql = "DELETE FROM Donation WHERE idDon = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
    }

    public void updateDonation(Don donation) {
        if (donation == null || !donation.isValid()) {
            throw new IllegalArgumentException("Données du don invalides");
        }

        String sql = "UPDATE Donation SET Montant = ?, typeDon = ?, dateDon = ?, idUser = ?, idProjet = ? WHERE idDon = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, donation.getMontant());
            preparedStatement.setString(2, donation.getTypeDon());
            preparedStatement.setDate(3, new java.sql.Date(donation.getDateDon().getTime()));
            preparedStatement.setInt(4, donation.getIdUser());
            preparedStatement.setInt(5, donation.getIdProjet());
            preparedStatement.setInt(6, donation.getIdDon());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'opération.Veuillez essayer plus tard");
        }
    }

}

