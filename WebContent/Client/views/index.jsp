<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>


<html lang="en">
  <head>
    <!-- Basic metas -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Les Produits</title>

    <!-- Styles -->
    <style>
        /* Reset and general styles */
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
        }

        ul li {
            margin: 0 15px;
        }

        ul li a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            padding: 10px 15px;
            display: inline-block;
        }

        ul li a:hover {
            color: #007BFF;
        }

        .header_section_top {
            background-color: #f8f9fa;
            padding: 10px 0;
        }

        .custom_menu {
            display: flex;
            justify-content: center;
        }

        .logo_section {
            text-align: center;
            padding: 20px 0;
        }

        .banner_taital {
            text-align: center;
            font-size: 36px;
            color: #444;
            margin-bottom: 20px;
        }

        .buynow_bt a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .buynow_bt a:hover {
            background-color: #0056b3;
        }

        /* Product section */
        #produits {
            margin: 50px auto;
            text-align: center;
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        img {
            width: 100px;
            height: auto;
        }

        h1 {
            color: #333;
        }

        /* Footer */
        .footer_section {
            background-color: #f8f9fa;
            padding: 20px 0;
            text-align: center;
        }

        .footer_menu ul {
            display: flex;
            justify-content: center;
            margin: 10px 0;
        }

        .footer_menu ul li {
            margin: 0 10px;
        }
        
        
    </style>

    <!-- External Styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Client/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Client/assets/css/style.css">
</head>
 <body>
      <!-- banner bg main start -->
 <div class="banner_bg_main">
        <div class="header_section_top">
            <div class="container">
               <div class="custom_menu">
    <% Long clientId = (Long) session.getAttribute("clientId"); %>
    
    <!-- Lien de déconnexion -->
    
    
    <ul class="menu-list">
        <!-- Formulaire pour afficher les commandes -->
        <li class="menu-item">
            <form action="commande?action=afficher&clientId=<%= clientId != null ? clientId : "" %>" method="post">
                <button type="submit" class="menu-button">Mes commandes</button>
            </form>
        </li>
        
        <!-- Formulaire pour afficher le panier -->
        <li class="menu-item">
            <form action="panier?action=afficher&clientId=<%= clientId != null ? clientId : "" %>" method="post">
                <button type="submit" class="menu-button">Mon Panier</button>
            </form>
        </li>
    </ul>
   
   <a style="color: white;" class="menu-item logout" href="${pageContext.request.contextPath}/AuthServlet?action=logout">Logout</a>
</div>

            </div>
        </div>

         <!-- header top section start -->
         <!-- logo section start -->
         <div class="logo_section">
            <a href="${pageContext.request.contextPath}/Client/views/index.jsp">
                <img src="${pageContext.request.contextPath}/Client/assets/images/logo.png" alt="Logo">
            </a>
        </div>

  <div class="banner_section layout_padding">
            <div class="container">
               <div id="my_slider" class="carousel slide" data-ride="carousel">
                  <div class="carousel-inner">
                     <div class="carousel-item active">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              <div class="buynow_bt"><a href="#produits">Buy Now</a></div>
                           </div>
                        </div>
                     </div>
                     <div class="carousel-item">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              <div class="buynow_bt"><a href="#produits">Buy Now</a></div>
                           </div>
                        </div>
                     </div>
                     <div class="carousel-item">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              <div class="buynow_bt"><a href="#produits">Buy Now</a></div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <a class="carousel-control-prev" href="#my_slider" role="button" data-slide="prev">
                  <i class="fa fa-angle-left"></i>
                  </a>
                  <a class="carousel-control-next" href="#my_slider" role="button" data-slide="next">
                  <i class="fa fa-angle-right"></i>
                  </a>
               </div>
            </div>
         </div>

<div id="produits">
    <h1>Liste de Tous les Produits</h1>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prix</th>
                <th>Description</th>
                <th>Image</th>
                   <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="produit" items="${produits}">
                <tr>
                    <td>${produit.nom}</td>
                    <td>${produit.prix}</td>
                    <td>${produit.description}</td>
                    <td>
                        <c:if test="${not empty produit.image}">
                            <img src="${produit.image}" alt="${produit.nom}">
                        </c:if>
                        <c:if test="${empty produit.image}">
                            Pas d'image
                        </c:if>
                    </td>
                    <td>
    <form action="panier?action=ajouter" method="post">
        <input type="hidden" name="produitId" value="${produit.idProduit}">
        <input type="hidden" name="clientId" value="${user_id}">
        <button type="submit">Ajouter au panier</button>
    </form>
</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
  <!-- Javascript files-->
      <script src="${pageContext.request.contextPath}/Client/assets/js/jquery.min.js"></script>
      <script src="${pageContext.request.contextPath}/Client/assets/js/popper.min.js"></script>
      <script src="${pageContext.request.contextPath}/Client/assets/js/bootstrap.bundle.min.js"></script>
      <script src="${pageContext.request.contextPath}/Client/assets/js/jquery-3.0.0.min.js"></script>
      <script src="${pageContext.request.contextPath}/Client/assets/js/plugin.js"></script>
      <!-- sidebar -->
      <script src="${pageContext.request.contextPath}/Client/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="${pageContext.request.contextPath}/Client/assets/js/custom.js"></script>
      <script>
         function openNav() {
           document.getElementById("mySidenav").style.width = "250px";
         }
         
         function closeNav() {
           document.getElementById("mySidenav").style.width = "0";
         }
      </script>
</body>
</html>
