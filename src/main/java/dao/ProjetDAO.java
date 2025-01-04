package dao;
import metier.Projet;

import java.sql.*;
import java.util.ArrayList;

public class ProjetDAO implements IProjet {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private final Connection connection;

    public ProjetDAO() throws SQLException, ClassNotFoundException {
        // Charger le driver
        Class.forName(DRIVER);
        // Établir la connexion
        this.connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    // Ajouter un projet
    public void addProject(Projet project) {
        String sql = "INSERT INTO Project (id, name, description, financial_goal, funds_raised,Date_Debut,Date_fin) VALUES (?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, project.getIdProjet());
            preparedStatement.setString(2, project.getNomProjet());
            preparedStatement.setString(3, project.getDescriptionProjet());
            preparedStatement.setDouble(4, project.getFinancialGoal());
            preparedStatement.setDouble(5, project.getFundsRaised());
            preparedStatement.setDate(6, new java.sql.Date(project.getDateDebut().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(project.getDateFin().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de l'ajout du projet. Veuillez réessayer.");
        }
    }

    // Récupérer un projet par son ID
    public Projet getProject(int id) {
        String sql = "SELECT * FROM Project WHERE id = ?";
        Projet project = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project = new Projet();
                project.setIdProjet(resultSet.getInt("idProjet"));
                project.setNomProjet(resultSet.getString("nomProjet"));
                project.setDescriptionProjet(resultSet.getString("descriptionProjet"));
                project.setFinancialGoal(resultSet.getDouble("financialGoal"));
                project.setFundsRaised(resultSet.getDouble("fundsRaised"));
                project.setDateDebut(resultSet.getDate("dateDebut"));
                project.setDateFin(resultSet.getDate("dateFin"));

            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la recherche du  projet. Veuillez réessayer.");
        }
        return project;
    }

    //Récupération des projects a partir de la liste
    public ArrayList<Projet> getAllProjects() {
        String sql = "SELECT * FROM projet";
        ArrayList<Projet> projets = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Projet project = new Projet();
                project.setId(resultSet.getInt("idProjet"));
                project.setName(resultSet.getString("nomProjet"));
                project.setDescription(resultSet.getString("descriptionProjet"));
                project.setFinancialGoal(resultSet.getDouble("financialGoal"));
                project.setFundsRaised(resultSet.getDouble("fundsRaised"));
                project.setDateDebut(resultSet.getDate("dateDebut"));
                project.setDateFin(resultSet.getDate("dateFin"));
                projets.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue afin d'obtenir les projects. Veuillez réessayer.");
        }
        return projets;
    }

    // Supprimer un projet par son  ID
    public void deleteProject(int id) {
        String sql = "DELETE FROM Project WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue pour supprimer  ce projet. Veuillez réessayer.");
        }
    }

    public void updateProject(Projet project) {
        String sql = "UPDATE Project SET nomProjet = ?, descriptionProjet = ?, financialGoal = ?, fundsRaised = ?, dateDebut = ?, dateFin = ?, idAssociation = ? WHERE idProjet = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, project.getNomProjet());
            preparedStatement.setString(2, project.getDescriptionProjet());
            preparedStatement.setDouble(3, project.getFinancialGoal());
            preparedStatement.setDouble(4, project.getFundsRaised());
            preparedStatement.setDate(5, new java.sql.Date(project.getDateDebut().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(project.getDateFin().getTime()));
            preparedStatement.setInt(8, project.getIdProjet());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la modification du projet. Veuillez réessayer.");
        }
    }
}


