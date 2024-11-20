<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Commandes</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Liste des Commandes</h1>

<table border="1">
    <thead>
        <tr>
            <th>ID Commande</th>
            <th>ID Client</th>
            <th>ID Produit</th>
            <th>Quantité</th>
            <th>ID Commerce</th>
            <th>Date Commande</th>
            <th>État</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="commande" items="${commandes}">
            <tr>
                <td>${commande.id_commande}</td>
                <td>${commande.id_client}</td>
                <td>${commande.id_produit}</td>
                <td>${commande.quantite}</td>
                <td>${commande.id_commerce}</td>
                <td>${commande.date_commande}</td>
                <td>${commande.etat}</td>
                <td>
                    <form action="LivreurCommande" method="post">
                        <input type="hidden" name="idCommande" value="${commande.id_commande}">
                        <input type="hidden" name="action" value="updateEtat">
                        <select name="nouvelEtat">
                            <option value="En cours" ${commande.etat == 'En cours' ? 'selected' : ''}>En cours</option>
                            <option value="Livré" ${commande.etat == 'Livré' ? 'selected' : ''}>Livré</option>
                        </select>
                        <button type="submit">Mettre à jour</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
