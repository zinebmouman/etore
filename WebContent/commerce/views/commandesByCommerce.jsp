<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/commerce/assets/img/favicon.png">
    <title>Commandes par Commerce</title>
    <!-- Fonts and icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" />
    <link href="${pageContext.request.contextPath}/commerce/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/commerce/assets/css/nucleo-svg.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
    <link id="pagestyle" href="${pageContext.request.contextPath}/commerce/assets/css/material-dashboard.css?v=3.2.0" rel="stylesheet" />
</head>

<body class="g-sidenav-show bg-gray-100">
    <jsp:include page="sidebar.jsp" />
    <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
        <jsp:include page="navbar.jsp" />
        <div class="container-fluid py-4">
            <h3 class="text-center mb-4">Vos Commandes </h3>
            <div class="row">
                <div class="col-12">
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-dark shadow-dark border-radius-lg pt-4 pb-3">
                                <h6 class="text-white text-capitalize ps-3">Liste des Commandes</h6>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive p-4">
                                <c:choose>
                                    <c:when test="${not empty commandes}">
                                        <table class="table align-items-center mb-0">
                                            <thead>
    <tr>
        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nom du Produit</th>
        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Image du Produit</th>
        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Quantité</th>
        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Date</th>
        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">État</th>
    </tr>
</thead>
<tbody>
    <c:forEach var="commande" items="${commandes}">
        <tr>
            <td>${commande.produit.nom}</td> <!-- Affichage du nom du produit -->
            <td>
                <img src="${pageContext.request.contextPath}/images/${commande.produit.image}" 
                     alt="${commande.produit.nom}" 
                     style="width: 100px; height: auto;" /> <!-- Affichage de l'image -->
            </td>
            <td>${commande.quantite}</td>
            <td>${commande.date_commande}</td>
            <td>${commande.etat}</td>
        </tr>
    </c:forEach>
</tbody>

                                        </table>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-center text-secondary">Aucune commande trouvée pour ce commerce.</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- JS Files -->
    <script src="${pageContext.request.contextPath}/commerce/assets/js/core/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/commerce/assets/js/core/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/commerce/assets/js/plugins/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/commerce/assets/js/material-dashboard.min.js?v=3.2.0"></script>
</body>
</html>
