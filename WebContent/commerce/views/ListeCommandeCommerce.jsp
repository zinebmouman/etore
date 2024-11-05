<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.JAVA.Beans.Commande" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Liste des Commandes</title>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" />
    <link href="${pageContext.request.contextPath}/admin/assets/css/nucleo-icons.css" rel="stylesheet" />
    <!-- Material Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link href="${pageContext.request.contextPath}/admin/assets/css/nucleo-svg.css" rel="stylesheet" />
    <link id="pagestyle" href="${pageContext.request.contextPath}/admin/assets/css/material-dashboard.css?v=3.2.0" rel="stylesheet" />
</head>
<body class="g-sidenav-show bg-gray-100">
    <jsp:include page="sidebar.jsp" />
    <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
        <jsp:include page="navbar.jsp" />
        <div class="container-fluid py-2">
            <h3 class="text-center">Liste des Commandes</h3>
            <hr>
            <div class="row">
                <div class="col-12">
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-dark shadow-dark border-radius-lg pt-4 pb-3">
                                <h6 class="text-white text-capitalize ps-3">Liste des Commandes</h6>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                        <tr>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">ID Commande</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Date Commande</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">État</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">ID Client</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">ID Commerce</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:choose>
                                            <c:when test="${not empty commandes}">
                                                <c:forEach var="commande" items="${commandes}">
                                                    <tr>
                                                        <td><c:out value="${commande.id_commande}" /></td>
                                                        <td><c:out value="${commande.date_commande}" /></td>
                                                        <td><c:out value="${commande.etat}" /></td>
                                                        <td><c:out value="${commande.id_client}" /></td>
                                                        <td><c:out value="${commande.id_commerce}" /></td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <tr>
                                                    <td colspan="5" class="text-center">Aucune commande trouvée.</td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </main>

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
    <script src="${pageContext.request.contextPath}/admin/assets/js/material-dashboard.min.js?v=3.2.0"></script>
</body>
</html>
