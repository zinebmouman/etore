<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/admin/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/admin/assets/img/favicon.png">
    <title>Modifier Livreur</title>
    <!-- Fonts and icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" />
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/admin/assets/css/nucleo-svg.css" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    
    <!-- Material Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0" />
    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/admin/assets/css/material-dashboard.css?v=3.2.0" rel="stylesheet" />
</head>

<body class="g-sidenav-show bg-gray-100">
    <jsp:include page="sidebar.jsp" />
    <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
        <jsp:include page="navbar.jsp" />
        <div class="container mt-5">
            <h4 class="font-weight-bolder">Modifier les informations du livreur</h4>
            <form action="${pageContext.request.contextPath}/Gererlivreur?action=update" method="post">
                <!-- ID du livreur (champ caché) -->
                <input type="hidden" name="idLivreur" value="${livreur.idLivreur}" />

                <!-- Champ Nom -->
                <div class="input-group input-group-outline my-3">
                    <label class="form-label" for="nom">Nom :</label>
                    <input type="text" id="nom" name="nom" class="form-control" 
                           value="${livreur.nom}" required />
                </div>

                <!-- Champ Statut -->
                <div class="input-group input-group-outline my-3">
                    <select id="statut" name="statut" class="form-control">
                        <option value="Disponible" ${livreur.statut == 'Disponible' ? 'selected' : ''}>Disponible</option>
                        <option value="Occupé" ${livreur.statut == 'Occupé' ? 'selected' : ''}>Occupé</option>
                    </select>
                </div>

                <!-- Champ Localisation -->
                <div class="input-group input-group-outline my-3">
                    <label class="form-label" for="localisation">Localisation :</label>
                    <input type="text" id="localisation" name="localisation" class="form-control" 
                           value="${livreur.localisation}" required />
                </div>

                <!-- Bouton de soumission -->
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary">Enregistrer les modifications</button>
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
