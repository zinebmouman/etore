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
    <!-- Header Section -->
    <div class="banner_bg_main">
        <div class="header_section_top">
            <div class="container">
                <div class="custom_menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/Client/views/Sign_in.jsp">Sign In</a></li>
                        <li><a href="${pageContext.request.contextPath}/Client/views/Sign_up.jsp">Sign Up</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Logo Section -->
        <div class="logo_section">
            <a href="${pageContext.request.contextPath}/Client/views/Home.jsp">
                <img src="${pageContext.request.contextPath}/Client/assets/images/logo.png" alt="Logo">
            </a>
        </div>

        <!-- Banner Section -->
        <div class="banner_section layout_padding">
            <h1 class="banner_taital">Get Started <br>Your Favorite Shopping</h1>
          
        </div>
    </div>
<div class="row">
    <c:forEach var="produit" items="${produits}">
        <div class="col-lg-4 col-sm-4">
            <div class="box_main">
                <h4 class="shirt_text">${produit.nom}</h4>
                <p class="price_text">Prix <span style="color: #262626;">${produit.prix} MAD</span></p>
                <div class="img">
                    <c:if test="${not empty produit.image}">
                        <img src="${produit.image}" alt="${produit.nom}" style="width: 100%; height: auto;">
                    </c:if>
                    <c:if test="${empty produit.image}">
                        <img src="images/default-img.png" alt="Image non disponible" style="width: 100%; height: auto;">
                    </c:if>
                </div>
               
            </div>
        </div>
    </c:forEach>
</div>

    <!-- Footer Section -->
    <div class="footer_section">
        
        <p>Help Line Number: <a href="#">+1 1800 1200 1200</a></p>
    </div>

    <!-- Scripts -->
    <script src="${pageContext.request.contextPath}/Client/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Client/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
