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
        
        .cart-container {
    width: 100%;
    border: 1px solid #ddd;
    padding: 20px;
    margin-top: 20px;
    border-radius: 10px;
    background-color: #f9f9f9;
}

.cart-header,
.cart-item {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
}

.cart-header {
    font-weight: bold;
    text-transform: uppercase;
    background-color: #f1f1f1;
    padding: 15px 0;
}

.cart-item:last-child {
    border-bottom: none;
}

.cart-item-name,
.cart-item-image,
.cart-item-price,
.cart-item-quantity,
.cart-item-actions {
    flex: 1;
    text-align: center;
}

.cart-item-image img {
    max-width: 80px;
    height: auto;
    border: 1px solid #ddd;
    border-radius: 5px;
}

.action-form {
    display: inline-block;
    margin: 0 5px;
}

.checkout-button {
    background-color: #5cb85c;
    color: #fff;
    padding: 10px 20px;
 
    border-radius: 5px;

    text-transform: uppercase;
}

.checkout-button:hover {
    background-color: #4cae4c;
}
     
    </style>
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
            width: 300px;
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
        
        .box_main {
    border: 1px solid #ddd;
    padding: 20px;
    margin-bottom: 20px;
    text-align: center;
    border-radius: 10px;
    background-color: #f9f9f9;
}

.shirt_text {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
}

.price_text {
    font-size: 16px;
    margin-bottom: 10px;
}

.img img {
    max-width: 100%;
    height: auto;
    margin-bottom: 20px;
}
/* Exemple de fond pour la page */
body {
    background-color: #f4f4f4; /* Remplacez par la couleur réelle de votre page */
}

/* Boutons avec le même fond que la page */
button, .btn, ul li a {
    background-color: inherit; /* Hérite la couleur de fond de la page */
    color: white; /* Texte sombre pour contraster */
    border: 2px solid #ccc; /* Bordure légère pour distinguer */
    border-radius: 5px; /* Coins arrondis */
    padding: 10px 15px; /* Ajustement des marges */
    font-size: 16px; /* Taille du texte */
    font-weight: bold; /* Texte en gras */
    cursor: pointer; /* Curseur pointer */
    text-decoration: none; /* Pas de soulignement */
    transition: all 0.3s ease; /* Animation fluide */
}

/* Effet au survol */
button:hover, .btn:hover, ul li a:hover {
    background-color: #ddd; /* Fond légèrement plus foncé au survol */
    color: #000; /* Texte plus sombre */
    border-color: #999; /* Bordure plus foncée */
}


}


        
    </style>

    <!-- External Styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Client/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Client/assets/css/style.css">

      <!-- banner bg main start -->
 <div class="banner_bg_main">
        <div class="header_section_top">
            <div class="container">
                <div class="custom_menu">
    <% Long clientId = (Long) session.getAttribute("clientId"); %>
    
    <ul>
           <li>
       <a class="btn"  class="btn btn-outline-primary btn-sm mb-0 me-3" href="${pageContext.request.contextPath}/listeProduits?clientId=<%= clientId != null ? clientId : "" %>">Home</a>
</li> 
        <li><form action="commande?action=afficher&clientId=<%= clientId != null ? clientId : "" %>" method="post">
            <button class="btn"  type="submit">Mes commandes</button>
        </form>
        </li>
       <li>
        
        <form action="panier?action=afficher&clientId=<%= clientId != null ? clientId : "" %>" method="post">
            <button class="btn"  type="submit">Mon Panier</button>
        </form>
        </li>
        <li>
         <a class="btn"  class="btn btn-outline-primary btn-sm mb-0 me-3"  href="${pageContext.request.contextPath}/AuthServlet?action=logout" >Logout</a>
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
           <!-- banner bg main start -->
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
        </head>
