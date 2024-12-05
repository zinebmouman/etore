<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>Eflyer</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Client/assets/css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Client/assets/css/style.css">
      <!-- Responsive-->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/Client/assets/css/responsive.css">
      <!-- fevicon -->
      <link rel="icon" href="${pageContext.request.contextPath}/Client/assets/images/fevicon.png" type="image/gif" />
      <!-- Scrollbar Custom CSS -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/Client/assets/css/jquery.mCustomScrollbar.min.css">
      <!-- Tweaks for older IEs-->
      <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
      <!-- fonts -->
      <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
      <!-- font awesome -->
      <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
      <!--  -->
      <!-- owl stylesheets -->
      <link href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext" rel="stylesheet">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/Client/assets/css/owl.carousel.min.css">
      <link rel="stylesoeet" href="${pageContext.request.contextPath}/Client/assets/css/owl.theme.default.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
   </head>
   <body>
      <!-- banner bg main start -->
      <div class="banner_bg_main">
         
         <!-- logo section start -->
         <div class="logo_section">
            <div class="container">
               <div class="row">
                  <div class="col-sm-12">
                     <div class="logo"><a href="${pageContext.request.contextPath}/listeProduits?page=home"><img src="${pageContext.request.contextPath}/Client/assets/images/logo.png"></a></div>
                  </div>
               </div>
            </div>
         </div>
         <!-- logo section end -->

         <!-- banner section start -->
         <div class="banner_section layout_padding">
            <div class="container">
               <div id="my_slider" class="carousel slide" data-ride="carousel">
                  <div class="carousel-inner">
                     <div class="carousel-item active">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              <form  action="${pageContext.request.contextPath}/listeProduits" method="GET">
    <input type="hidden" name="page" value="home">
    <button class="buynow_bt" type="submit">Buy Now</button>
</form>
                           </div>
                        </div>
                     </div>
                     <div class="carousel-item">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              <form  action="${pageContext.request.contextPath}/listeProduits" method="GET">
    <input type="hidden" name="page" value="home">
    <button class="buynow_bt" type="submit">Buy Now</button>
</form></div>
                           </div>
                        </div>
                     </div>
                     <div class="carousel-item">
                        <div class="row">
                           <div class="col-sm-12">
                              <h1 class="banner_taital">Get Start <br>Your favriot shoping</h1>
                              <form  action="${pageContext.request.contextPath}/listeProduits" method="GET">
    <input  type="hidden" name="page" value="home">
    <button  class="buynow_bt" type="submit">Buy Now</button>
</form>
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
         <!-- banner section end -->
      </div>
      <!-- banner bg main start -->

   <style>
   .banner_bg_main {
    width: 100%; /* Prend toute la largeur */
    height: 100vh; /* Prend toute la hauteur de la fenêtre */
    display: flex; /* Aligne le contenu au centre si besoin */
    flex-direction: column; /* Pour organiser verticalement */
    justify-content: space-between; /* Aligne le contenu */
    align-items: center; /* Centre horizontalement */
    background-size: cover; /* S'assure que l'image de fond couvre tout */
    background-repeat: no-repeat; /* Évite les répétitions */
}
.buynow_bt {
    background-color: black;
    color: white;
    font-size: 14px; /* Réduction de la taille de la police */
    padding: 10px 20px; /* Ajustement des marges internes */
    border: none;
    border-radius: 5px; /* Bouton arrondi */
    cursor: pointer;
    display: inline-block; /* Nécessaire pour le centrer */
    margin: auto; /* Pour un centrage horizontal */
}

.buynow_bt:hover {
    background-color: darkgray; /* Changement de couleur au survol */
}

.carousel-item .row {
    display: flex;
    justify-content: center; /* Centre horizontalement */
    align-items: center; /* Centre verticalement */
    height: 100%; /* S'assure que le contenu prend toute la hauteur */
    text-align: center; /* Centre le texte */
}

   
   </style>
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