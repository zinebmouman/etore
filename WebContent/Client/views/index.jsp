<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>


<html lang="en">
<jsp:include page="Header.jsp" />
<style>
.btn_main {
    display: flex;
    justify-content: space-around;
    margin-top: 15px;
}

.buy_bt button, .seemore_bt a {
    text-decoration: none;
    color: #fff;
    background-color: #5cb85c;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.buy_bt button:hover, .seemore_bt a:hover {
    background-color: #4cae4c;
    
}

</style>
 <body>





<div class="row">
    <c:forEach var="produit" items="${produits}">
        <div class="col-lg-4 col-sm-4">
            <div class="box_main">
                <h4 class="shirt_text">${produit.nom}</h4>
                <p class="price_text">Prix <span style="color: #262626;">${produit.prix} MAD</span></p>
                <div class="img">
                    <c:if test="${not empty produit.image}">
                        <img src="${produit.image}" alt="${produit.nom}">
                    </c:if>
                    <c:if test="${empty produit.image}">
                        <img src="images/default-img.png" alt="Image non disponible">
                    </c:if>
                </div>
                <div class="btn_main">
                    <div class="buy_bt">
                        <form action="panier?action=ajouter" method="post">
                            <input type="hidden" name="produitId" value="${produit.idProduit}">
                            <input type="hidden" name="clientId" value="${user_id}">
                            <button type="submit">Ajouter au panier</button>
                        </form>
                    </div>
                    
                </div>
            </div>
        </div>
    </c:forEach>
</div>

  <!-- Footer Section -->
    <div class="footer_section">
      
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
