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
 
    <ul>
      
        <li>
         <a class="btn btn-outline-primary btn-sm mb-0 me-3"  href="${pageContext.request.contextPath}/AuthServlet?action=logout" >Logout</a>
         </li>
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
                             
                           </div>
                        </div>
                     </div>
                     <div class="carousel-item">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                            
                           </div>
                        </div>
                     </div>
                     <div class="carousel-item">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              
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
         </div>
        
        <div class="row">
    <% Long clientId = (Long) session.getAttribute("clientId"); %>

    <c:choose>
        <c:when test="${not empty commandes}">
          
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
        </c:when>
        <c:otherwise>
            <p>Aucune commande trouvée.</p>
        </c:otherwise>
    </c:choose>
</div>


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
