package com.JAVA.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.JAVA.Beans.Commande;
import com.JAVA.DAO.CommandeDAO;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {
	private CommandeDAO CommandeDAO;
   
    public void init() {
      this.CommandeDAO=DAOFactory.getInstance().getCommandeDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("clientId");

        System.out.println("Action reçue : " + action);
        System.out.println("ID du client (session) : " + clientId);

        try {
            switch (action) {
                case "afficher":
            
              if (clientId != null) {
            // Récupérer les commandes du client
            List<Commande> commandes = CommandeDAO.getCommandesByClientId(clientId);

            System.out.println("Nombre de commandes récupérées pour le client " + clientId + " : " + commandes.size());

            // Ajouter la liste des commandes aux attributs de la requête
            request.setAttribute("commandes", commandes);

            // Rediriger vers la page JSP pour afficher les commandes
            request.getRequestDispatcher("/Client/views/mesCommandes.jsp").forward(request, response);
        } else {
            System.out.println("Client ID non valide. Redirection vers la page de connexion.");
            response.sendRedirect("login.jsp");
        }
                break; 
                case "modifierEtatCommande":
                    if (clientId != null) {
                        try {
                            // Récupérer l'ID de la commande et le nouvel état
                            long idCommande = Long.parseLong(request.getParameter("idCommande"));
                            String nouvelEtat = request.getParameter("nouvelEtat");

                            System.out.println("Commande ID : " + idCommande);
                            System.out.println("Nouvel état : " + nouvelEtat);

                            // Mise à jour de l'état de la commande
                            CommandeDAO.updateCommandeEtat(idCommande, nouvelEtat);

                            // Récupérer et afficher la liste des commandes mises à jour
                            List<Commande> commandes = CommandeDAO.getCommandesByClientId(clientId);
                            request.setAttribute("commandes", commandes);

                            // Renvoyer à la page JSP sans changer d'URL
                            request.getRequestDispatcher("/Client/views/mesCommandes.jsp").forward(request, response);
                        } catch (NumberFormatException e) {
                            throw new ServletException("ID de commande invalide.", e);
                        } catch (DAOException e) {
                            throw new ServletException("Erreur lors de la mise à jour de l'état de la commande.", e);
                        }
                    } else {
                        response.sendRedirect("login.jsp"); // Rediriger si l'utilisateur n'est pas connecté
                    }
                    break;

}
}
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la gestion du panier", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);  // Redirigez tous les appels GET vers doPost
    }}
