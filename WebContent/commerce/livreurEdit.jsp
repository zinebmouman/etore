<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editer un Livreur</title>
</head>
<body>
    <h1>Editer Livreur</h1>
    <form action="${pageContext.request.contextPath}/Gererlivreur?action=update" method="post">
        <input type="hidden" name="id" value="${livreur.idLivreur}">
        Nom: <input type="text" name="nom" value="${livreur.nom}" required><br>
        Statut: <input type="text" name="statut" value="${livreur.statut}" required><br>
        Localisation: <input type="text" name="localisation" value="${livreur.localisation}" required><br>
        <button type="submit">Enregistrer</button>
    </form>
</body>
</html>
