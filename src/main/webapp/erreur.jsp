<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        .error-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #e74c3c;
        }
        p {
            font-size: 16px;
            line-height: 1.5;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: #ffffff;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Une erreur est survenue</h1>
    <p>${errorMessage != null ? errorMessage : "Nous sommes désolés, mais une erreur inattendue s est produite."}</p>
    <a href="index.jsp">Retour à laccueil </a>
</div>
</body>
</html>
