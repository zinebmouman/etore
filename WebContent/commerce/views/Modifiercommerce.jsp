<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/admin/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/admin/assets/img/favicon.png">
    <title>Modifier Commerce</title>
    <!-- Fonts and icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" />
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/admin/assets/css/nucleo-svg.css" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/admin/assets/css/material-dashboard.css?v=3.2.0" rel="stylesheet" />
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>

<body class="g-sidenav-show bg-gray-100">
    <jsp:include page="sidebar.jsp" />
    <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
        <jsp:include page="navbar.jsp" />
        <div class="container mt-5">
            <h4 class="font-weight-bolder">Modifier les informations du commerce</h4>
            <form action="${pageContext.request.contextPath}/Gerercommercier?action=update" method="post">
                <!-- ID du commerce (champ caché) -->
                <input type="hidden" name="id_commerce" value="${commerce.id_commerce}" />

                <!-- Champ Nom -->
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" id="nom" name="nom" class="form-control" 
                           value="${commerce.nom}" required />
                </div>

                <!-- Champ Adresse -->
                <div class="form-group">
                    <label for="adresse">Adresse :</label>
                    <input type="text" id="adresse" name="adresse" class="form-control" 
                           value="${commerce.adresse}" required />
                </div>

                <!-- Champ Contact -->
                <div class="form-group">
                    <label for="contact">Contact :</label>
                    <input type="text" id="contact" name="contact" class="form-control" 
                           value="${commerce.contact}" required />
                </div>

                <!-- Champ Type de Commerce -->
                <div class="form-group">
                    <label for="type_commerce">Type de Commerce :</label>
                    <input type="text" id="type_commerce" name="type_commerce" class="form-control" 
                           value="${commerce.type_commerce}" required />
                </div>

                <!-- Bouton de soumission -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Enregistrer les modifications</button>
                    <a href="${pageContext.request.contextPath}/listeCommerces" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/admin/assets/js/core/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/js/core/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/js/plugins/perfect-scrollbar.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/js/plugins/smooth-scrollbar.min.js"></script>
        <script>
            var win = navigator.platform.indexOf('Win') > -1;
            if (win && document.querySelector('#sidenav-scrollbar')) {
                var options = {
                    damping: '0.5'
                }
                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
            }
        </script>
        <!-- Control Center for Material Dashboard -->
        <script src="${pageContext.request.contextPath}/admin/assets/js/material-dashboard.min.js?v=3.2.0"></script>
    </main>
</body>
</html>
