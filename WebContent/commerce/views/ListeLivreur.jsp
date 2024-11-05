<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/admin/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/admin/assets/img/favicon.png">
    <title>Admin</title>
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

        <div class="container-fluid py-2">
            <h3 class="text-center">Liste des Livreurs</h3>
            <hr>
            <div class="text-left mb-3">
                <a href="${pageContext.request.contextPath}/admin/views/AjouterLivreur.jsp" class="btn btn-success">Ajouter un nouveau livreur</a>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-dark shadow-dark border-radius-lg pt-4 pb-3">
                                <h6 class="text-white text-capitalize ps-3">Liste des Livreurs</h6>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                        <tr>
                                            
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Nom</th>
                                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Statut</th>
                                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Localisation</th>
                                            <th class="text-secondary opacity-7"></th>
                                             <th class="text-secondary opacity-7"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="livreur" items="${livreurs}">
                                            <tr>
                                                
                                                <td>
                                                    <div class="d-flex px-2 py-1">
                                                        <div>
                                                            <img src="${pageContext.request.contextPath}/admin/assets/img/default-avatar.png" class="avatar avatar-sm me-3 border-radius-lg" alt="livreur-avatar">
                                                        </div>
                                                        <div class="d-flex flex-column justify-content-center">
                                                            <h6 class="mb-0 text-sm"><c:out value="${livreur.nom}" /></h6>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="badge badge-sm bg-gradient-success"><c:out value="${livreur.statut}" /></span>
                                                </td>
                                                <td class="align-middle text-center"><c:out value="${livreur.localisation}" /></td>
                                                <td class="align-middle">
                                                    <a href="${pageContext.request.contextPath}/Gererlivreur?action=edit&id=${livreur.idLivreur}" 	class="btn btn-danger btn-sm" data-toggle="tooltip"    data-original-title="Edit user">
    													Editer
													</a>

                                                </td>
                                                <td class="align-middle">
                                                    <a href="${pageContext.request.contextPath}/Gererlivreur?action=delete&id=${livreur.idLivreur}" 
                               class="btn btn-primary"  
                               onclick="return confirmDelete();">Supprimer</a>

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

        <!-- Core JS Files -->
        <script>
    function confirmDelete() {
        return confirm("Êtes-vous sûr de vouloir supprimer ce livreur ?");
    }
    </script>
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
        <!-- Github buttons -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
        <!-- Control Center for Material Dashboard -->
        <script src="${pageContext.request.contextPath}/admin/assets/js/material-dashboard.min.js?v=3.2.0"></script>
    </main>
</body>
</html>
