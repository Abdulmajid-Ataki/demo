<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Utilisateurs</title>
    <link rel="stylesheet" href="styles.css"> <!-- Fichier CSS externe -->
</head>
<body>
<h1>Liste des Utilisateurs</h1>
<table class="styled-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Email</th>
        <th>Telephone</th>
        <th>Adresse</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.idUser}</td>
            <td>${user.nomUser}</td>
            <td>${user.prenomUser}</td>
            <td>${user.email}</td>
            <td>${user.telephone}</td>
            <td>${user.adresse}</td>
            <td>${user.role}</td>
            <td>
                <a href="User?action=edit&idUser=${user.idUser}">Modifier</a> |
                <a href="User?action=delete&idUser=${user.idUser}"
                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Ajouter un Utilisateur</h2>
<form method="post" action="User?action=add" class="form-style">
    <label for="nomUser">Nom</label>
    <input type="text" id="nomUser" name="nomUser" placeholder="Nom" required>

    <label for="prenomUser">Prenom</label>
    <input type="text" id="prenomUser" name="prenomUser" placeholder="Prénom" required>

    <label for="email">Email</label>
    <input type="email" id="email" name="email" placeholder="Email" required>

    <label for="telephone">Telephone</label>
    <input type="text" id="telephone" name="telephone" placeholder="Téléphone" required pattern="^\\+?[0-9]{10,15}$" title="Entrez un numéro de téléphone valide">

    <label for="adresse">Adresse</label>
    <input type="text" id="adresse" name="adresse" placeholder="Adresse" required>

    <label for="role">Role</label>
    <select id="role" name="role" required>
        <option value="ADMIN">Admin</option>
        <option value="DONATEUR">Donateur</option>
        <option value="GESTIONNAIRE">Gestionnaire</option>
    </select>

    <label for="idAssociation">ID Association</label>
    <input type="number" id="idAssociation" name="idAssociation" placeholder="ID Association" min="1" required>

    <button type="submit">Ajouter</button>
</form>
</body>
</html>
