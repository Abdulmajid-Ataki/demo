package com.example.project;



import metier.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "User", value = "/User")
public class UserServlet extends HttpServlet {

    // Paramètres de connexion à la base de données
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestion_dons";
    private static final String DB_USER = "root"; // Remplacez par votre utilisateur MySQL
    private static final String DB_PASSWORD = ""; // Remplacez par votre mot de passe MySQL

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listUsers(req, resp);
                    break;
                default:
                    listUsers(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<User> users = new ArrayList<>();

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM user";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Récupération des utilisateurs
                while (resultSet.next()) {
                    User user = new User();
                    user.setIdUser(resultSet.getInt("idUser"));
                    user.setNomUser(resultSet.getString("nomUser"));
                    user.setPrenomUser(resultSet.getString("prenomUser"));
                    user.setEmail(resultSet.getString("email"));
                    user.setTelephone(resultSet.getString("telephone"));
                    user.setAdresse(resultSet.getString("adresse"));
                    user.setDateInscription(resultSet.getDate("dateInscription"));
                    user.setRole(User.Role.valueOf(resultSet.getString("role")));

                    users.add(user);
                }
            }
        }

        // Passer les utilisateurs à la JSP
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }
}
