<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>


<html lang="en">
<jsp:include page="Header.jsp" />
 <body>





<div class="row">

  <% Long clientId = (Long) session.getAttribute("clientId"); %>

<c:choose>
    <c:when test="${not empty requestScope.produitsPanier}">
        <div class="cart-container">
            <div class="cart-header">
                <div class="cart-item-name">Nom du produit</div>
                <div class="cart-item-image">Image</div>
                <div class="cart-item-price">Prix</div>
                <div class="cart-item-quantity">Quantité</div>
                <div class="cart-item-actions">Actions</div>
            </div>
            <c:forEach var="panier" items="${requestScope.produitsPanier}">
                <div class="cart-item">
                    <div class="cart-item-name">${panier.produit.nom}</div>
                    <div class="cart-item-image">
                        <img src="${panier.produit.image}" alt="${panier.produit.nom}" />
                    </div>
                    <div class="cart-item-price">${panier.produit.prix} MAD</div>
                    <div class="cart-item-quantity">${panier.quantite}</div>
                    <div class="cart-item-actions">
                        <form action="panier?action=modifier&clientId=<%= clientId != null ? clientId : "" %>" method="post" class="action-form">
                            <input type="number" name="quantite" value="${panier.quantite}" min="1" />
                            <input type="hidden" name="action" value="modifier" />
                            <input type="hidden" name="produitId" value="${panier.produitId}" />
                            <button type="submit">Modifier</button>
                        </form>
                        <form action="panier?action=supprimer&clientId=<%= clientId != null ? clientId : "" %>" method="post" class="action-form">
                            <input type="hidden" name="action" value="supprimer" />
                            <input type="hidden" name="produitId" value="${panier.produitId}" />
                            <button type="submit">Supprimer</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <div class="cart-footer">
                <form action="panier?action=commander&clientId=<%= clientId != null ? clientId : "" %>" method="post">
                    <button type="submit" class="checkout-button">Commander</button>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <p>Votre panier est vide.</p>
    </c:otherwise>
</c:choose>


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