<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.List" %>
<%@ page import="com.JAVA.Beans.PanierBean" %>

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
    
    <ul>
        
        <form action="commande?action=afficher&clientId=<%= clientId != null ? clientId : "" %>" method="post">
            <button type="submit">Mes commandes</button>
        </form>
        
       
        
        <form action="panier?action=afficher&clientId=<%= clientId != null ? clientId : "" %>" method="post">
            <button type="submit">Mon Panier</button>
        </form>
    </ul>
       
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
         <c:choose>
    <c:when test="${not empty requestScope.produitsPanier}">
        <table>
            <thead>
                <tr>
                    <th>Nom du produit</th>
                    <th>Image</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                    <th>Modifier Quantité</th>
                    <th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="panier" items="${requestScope.produitsPanier}">
                    <tr>
                        <td>${panier.produit.nom}</td>
                        <td><img src="${panier.produit.image}" alt="${panier.produit.nom}"/></td>
                        <td>${panier.produit.prix} MAD</td>
                        <td>${panier.quantite}</td>
                        <td>
                            <form action="panier?action=modifier&clientId=<%= clientId != null ? clientId : "" %>"  method="post">
                                <input type="number" name="quantite" value="${panier.quantite}" min="1" />
                                <input type="hidden" name="action" value="modifier" />
                                <input type="hidden" name="produitId" value="${panier.produitId}" />

                                <button type="submit">Modifier</button>
                            </form>
                        </td>
                        <td>
                            <form action="panier?action=supprimer&clientId=<%= clientId != null ? clientId : "" %>" method="post">
                                <input type="hidden" name="action" value="supprimer" />
                                 <input type="hidden" name="produitId" value="${panier.produitId}" />
                                <button type="submit">Supprimer</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      <form action="panier?action=commander&clientId=<%= clientId != null ? clientId : "" %>" method="post">
         <input type="hidden" name="produitId" value="${panier.id}" />
    <button type="submit">Commander</button>
</form>
    </c:when>
    <c:otherwise>
        <p>Votre panier est vide.</p>
    </c:otherwise>
</c:choose>
   <!-- Footer Section -->
    <div class="footer_section">
        <div class="footer_menu">
            <ul>
                <li><a href="#">Best Sellers</a></li>
                <li><a href="#">Gift Ideas</a></li>
                <li><a href="#">New Releases</a></li>
                <li><a href="#">Today's Deals</a></li>
                <li><a href="#">Customer Service</a></li>
            </ul>
        </div>
        <p>Help Line Number: <a href="#">+1 1800 1200 1200</a></p>
    </div>

</body>
</html>
