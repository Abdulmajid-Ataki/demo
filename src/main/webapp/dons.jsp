<%@ page import="metier.Don" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Dons</title>
    <link rel="stylesheet" href="styles.css"> <!-- Lien vers un fichier CSS externe -->
</head>
<body>
<h1>Liste des Dons</h1>

<table class="styled-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Montant</th>
        <th>Type de Don</th>
        <th>Date du Don</th>
        <th>ID Utilisateur</th>
        <th>ID Projet</th>
<%--        // <th>Actions</th>--%>
    </tr>
    </thead>
    <tbody>

    <%
        List<Don> dons = (List<Don>) request.getAttribute("dons");
        if (dons == null || dons.isEmpty()) {
    %>
    <tr>
        <td colspan="6">Aucun don trouve.</td>
    </tr>
    <%
    } else {
        for (Don don : dons) {
    %>
    <tr>
        <td><%= don.getIdDon() %></td>
        <td><%= don.getMontant() %></td>
        <td><%= don.getTypeDon() %></td>
        <td><%= don.getDateDon() %></td>
        <td><%= don.getIdUser() %></td>
        <td><%= don.getIdProjet() %></td>
    </tr>
    <%
            }
        }
    %>

    </tbody>
</table>

<h2>Ajouter un Don</h2>
<form method="post" action="dons?action=add" class="form-style">
    <label for="montant">Montant</label>
    <input type="number" id="montant" name="montant" placeholder="Montant" min="0" required>

    <label for="typeDon">Type de Don</label>
    <input type="text" id="typeDon" name="typeDon" placeholder="Type de Don" required>

    <label for="dateDon">Date du Don</label>
    <input type="date" id="dateDon" name="dateDon" required>

    <label for="idUser">ID Utilisateur</label>
    <input type="number" id="idUser" name="idUser" placeholder="ID Utilisateur" min="1" required>

    <label for="idProjet">ID Projet</label>
    <input type="number" id="idProjet" name="idProjet" placeholder="ID Projet" min="1" required>


    <button type="submit">Ajouter</button>
</form>
</body>
</html>

