<%@ page import="metier.Projet" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Projets</title>
    <link rel="stylesheet" href="styles.css"> <!-- Fichier CSS externe -->
</head>
<body>
<h1>Liste des Projets</h1>
<table class="styled-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom du Projet</th>
        <th>Description</th>
        <th>Objectif Financier</th>
        <th>Fonds Levés</th>
        <th>Date de Début</th>
        <th>Date de Fin</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Projet> projets = (List<Projet>) request.getAttribute("projets");
        if (projets == null || projets.isEmpty()) {
    %>
    <tr>
        <td colspan="6">Aucun projet trouve.</td>
    </tr>
    <%
    } else {
        for (Projet projet : projets) {
    %>
    <tr>
        <td><%= projet.getIdProjet() %></td>
        <td><%= projet.getNomProjet() %></td>
        <td><%= projet.getDescriptionProjet() %></td>
        <td><%= projet.getFinancialGoal() %></td>
        <td><%= projet.getFundsRaised() %></td>
        <td><%= projet.getDateDebut() %></td>
        <td><%= projet.getDateFin() %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<h2>Ajouter un Projet</h2>
<form method="post" action="projet?action=add" class="form-style">
    <label for="nomProjet">Nom du Projet</label>
    <input type="text" id="nomProjet" name="nomProjet" placeholder="Nom du Projet" required>

    <label for="descriptionProjet">Description</label>
    <textarea id="descriptionProjet" name="descriptionProjet" placeholder="Description du Projet" required></textarea>

    <label for="financialGoal">Objectif Financier</label>
    <input type="number" id="financialGoal" step="0.01" name="financialGoal" placeholder="Objectif Financier" required>

    <label for="fundsRaised">Fonds Levés</label>
    <input type="number" id="fundsRaised" step="0.01" name="fundsRaised" placeholder="Fonds Levés" required>

    <label for="dateDebut">Date de Début</label>
    <input type="date" id="dateDebut" name="dateDebut" required>

    <label for="dateFin">Date de Fin</label>
    <input type="date" id="dateFin" name="dateFin" required>




    <button type="submit">Ajouter</button>
</form>
</body>
</html>
