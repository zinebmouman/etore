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


    <% Long clientId = (Long) session.getAttribute("clientId"); %>
   
      
    <c:choose>
        <c:when test="${not empty commandes}">
            <table>
                <thead>
                    <tr>
                        
                        <th>Date de commande</th>
                        <th>État</th>
                        <th>Produit</th>
                        <th>Quantité</th>
                        <th>Commerce</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="commande" items="${commandes}">
                        <tr>
                           
                            <td>${commande.date_commande}</td>
                            <td>${commande.etat}</td>
                            <td>${commande.id_produit}</td>
                            <td>${commande.quantite}</td>
                            <td>${commande.id_commerce}</td>
                            
                     <td>
                    <form action="commande" method="post">
                        <input type="hidden" name="idCommande" value="${commande.id_commande}">
                        <input type="hidden" name="action" value="modifierEtatCommande">
                        <select name="nouvelEtat">
                            <option value="En cours" ${commande.etat == 'en attente' ? 'selected' : ''}>En attente</option>
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