<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/commerce/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/commerce/assets/img/favicon.png">
    <title>Liste des Produits</title>
    <!-- Fonts and icons -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" />
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/commerce/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/commerce/assets/css/nucleo-svg.css" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- Material Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0" />
    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/commerce/assets/css/material-dashboard.css?v=3.2.0" rel="stylesheet" />
</head>

<body class="g-sidenav-show bg-gray-100">
    <jsp:include page="sidebar.jsp" />
    <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg">
        <jsp:include page="navbar.jsp" />
        <div class="container-fluid py-2">
            <h3 class="text-center">Liste des Produits</h3>
            <hr>
            <div class="text-left mb-3">
                <a href="${pageContext.request.contextPath}/GererProduit?action=showAddForm&id_commerce=${param.id_commerce}" class="btn btn-success">Ajouter un nouveau produit</a>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-dark shadow-dark border-radius-lg pt-4 pb-3">
                                <h6 class="text-white text-capitalize ps-3">Liste des Produits</h6>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                        <tr><th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Image</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nom</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Description</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Prix</th>
                                            <th class="text-secondary opacity-7"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="produit" items="${produits}">
                                            <tr><td><img src="${pageContext.request.contextPath}/images/${produit.image}" alt="${produit.nom}"/></td>
                                                <td>${produit.nom}</td>
                                                <td>${produit.description}</td>
                                                <td>${produit.prix}</td>
                                                <td>
                                                    <a href="<c:url value='/GererProduit?action=edit&id_produit=${produit.idProduit}&id_commerce=${param.id_commerce}' />" class="btn btn-danger btn-sm">Modifier</a>
                                                   
                                                <form action="${pageContext.request.contextPath}/GererProduit" method="post" style="display: inline;" onsubmit="return confirmDelete();">
                    <input type="hidden" name="id_commerce" value="${param.id_commerce}">
                     <input type="hidden" name="id_produit" value="${produit.idProduit}">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Supprimer" class="btn btn-primary" data-toggle="tooltip" data-original-title="Supprimer le commerce">
                </form>
                                                
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
<script>
            function confirmDelete() {
                return confirm("Êtes-vous sûr de vouloir supprimer ce commerce ?");
            }
        </script>
    <!-- Core JS Files -->
    <script src="${pageContext.request.contextPath}/commerce/assets/js/core/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/commerce/assets/js/core/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/commerce/assets/js/plugins/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/commerce/assets/js/plugins/smooth-scrollbar.min.js"></script>
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
    <script src="${pageContext.request.contextPath}/commerce/assets/js/material-dashboard.min.js?v=3.2.0"></script>
</body>
</html>
