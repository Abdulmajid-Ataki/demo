package com.example.project;

import dao.ProjetDAO;
import jakarta.servlet.RequestDispatcher;
import metier.Projet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "projet", value = "/projet")
public class ProjetServlet extends HttpServlet {

    // Paramètres de connexion à la base de données
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String DB_USER = "root"; // Remplacez par votre utilisateur MySQL
    private static final String DB_PASSWORD = ""; // Remplacez par votre mot de passe MySQL

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Projet> projets = new ArrayList<>();

        try {
            ProjetDAO projetDAO = new ProjetDAO();
            projets = projetDAO.getAllProjects();
            System.out.println("Liste des projets : " + projets);
            req.setAttribute("projets", projets);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Projet.jsp");
            dispatcher.forward(req, resp);
        } catch (ClassNotFoundException e) {
            throw new ServletException("Driver JDBC introuvable", e);
        } catch (SQLException e) {
            throw new ServletException("Erreur SQL", e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String nomProjet = req.getParameter("nomProjet");
        String descriptionProjet = req.getParameter("descriptionProjet");
        String financialGoalStr = req.getParameter("financialGoal");
        String fundsRaisedStr = req.getParameter("fundsRaised");
        String dateDebutStr = req.getParameter("dateDebut");
        String dateFinStr = req.getParameter("dateFin");

        // Valider les données
        if (nomProjet == null || descriptionProjet == null || dateDebutStr == null || dateFinStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters.");
            return;
        }
        double financialGoal, fundsRaised;
        java.sql.Date dateDebut, dateFin;

        try {
            financialGoal = Double.parseDouble(financialGoalStr);
            fundsRaised = Double.parseDouble(fundsRaisedStr);
            dateDebut = java.sql.Date.valueOf(dateDebutStr);
            dateFin = java.sql.Date.valueOf(dateFinStr);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters.");
            return;
        }

        // Ajouter le projet dans la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO Project (nomProjet, descriptionProjet, financialGoal , fundsRaised, dateDebut, dateFin) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomProjet);
                preparedStatement.setString(2, descriptionProjet);
                preparedStatement.setDouble(3, financialGoal);
                preparedStatement.setDouble(4, fundsRaised);
                preparedStatement.setDate(3, dateDebut);
                preparedStatement.setDate(4, dateFin);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Rediriger vers la liste des projets ou afficher un message de confirmation
        resp.sendRedirect(req.getContextPath() + "/projet?action=list");
    }
    private void listProjets(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Projet> projets = new ArrayList<>();

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM projet";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Récupération des projets
                while (resultSet.next()) {
                    Projet projet = new Projet();
                    projet.setId(resultSet.getInt("idProjet"));
                    projet.setNomProjet(resultSet.getString("nomProjet"));
                    projet.setDescriptionProjet(resultSet.getString("descriptionProjet"));
                    projet.setFinancialGoal(resultSet.getDouble("financialGoal"));
                    projet.setFundsRaised(resultSet.getDouble("fundsRaised"));
                    projet.setDateDebut(resultSet.getDate("dateDebut"));
                    projet.setDateFin(resultSet.getDate("dateFin"));

                    projets.add(projet);
                }
            }
        }

        // Passer les projets à la JSP
        req.setAttribute("projets", projets);
        req.getRequestDispatcher("/WEB-INF/projets.jsp").forward(req, resp);
    }
}
