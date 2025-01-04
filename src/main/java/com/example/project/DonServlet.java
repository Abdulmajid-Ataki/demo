package com.example.project;

import dao.DonDao;
import jakarta.servlet.RequestDispatcher;
import metier.Don;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dons")
public class DonServlet extends HttpServlet {

    // Paramètres de connexion à la base de données
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DonDao donDao = new DonDao();
            List<Don> dons = donDao.getAllDonations();
            System.out.println("Liste des dons : " + dons);
            req.setAttribute("dons", dons);
            RequestDispatcher dispatcher = req.getRequestDispatcher("dons.jsp");
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
        String montantStr = req.getParameter("montant");
        String typeDon = req.getParameter("typeDon");
        String dateDonStr = req.getParameter("dateDon");
        String idUserStr = req.getParameter("idUser");
        String idProjetStr = req.getParameter("idProjet");

        // Valider les données
        if (montantStr == null || typeDon == null || dateDonStr == null || idUserStr == null || idProjetStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters.");
            return;
        }

        double montant;
        int idUser, idProjet;
        java.sql.Date dateDon;

        try {
            montant = Double.parseDouble(montantStr);
            idUser = Integer.parseInt(idUserStr);
            idProjet = Integer.parseInt(idProjetStr);
            dateDon = java.sql.Date.valueOf(dateDonStr);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters.");
            return;
        }

        // Ajouter le don dans la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO don (montant, typeDon, dateDon, idUser, idProjet) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, montant);
                preparedStatement.setString(2, typeDon);
                preparedStatement.setDate(3, dateDon);
                preparedStatement.setInt(4, idUser);
                preparedStatement.setInt(5, idProjet);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Rediriger vers la liste des dons ou afficher un message de confirmation
        resp.sendRedirect(req.getContextPath() + "/dons?action=list");
    }

    private void listDons(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Don> dons = new ArrayList<>();

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM don";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Récupération des dons
                while (resultSet.next()) {
                    Don don = new Don();
                    don.setIdDon(resultSet.getInt("idDon"));
                    don.setMontant(resultSet.getDouble("montant"));
                    don.setTypeDon(resultSet.getString("typeDon"));
                    don.setDateDon(resultSet.getDate("dateDon"));
                    don.setIdUser(resultSet.getInt("idUser"));
                    don.setIdProjet(resultSet.getInt("idProjet"));


                    dons.add(don);
                }
            }
        }

        // Passer les dons à la JSP
        req.setAttribute("dons", dons);
        req.getRequestDispatcher("dons.jsp").forward(req, resp);
    }
}
