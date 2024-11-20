package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.JAVA.Beans.Commande;
import com.JAVA.DAO.CommandeDAO;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

/**
 * Servlet implementation class LivreurCommande
 */
@WebServlet("/LivreurCommande")
public class LivreurCommande extends HttpServlet {
    private CommandeDAO CommandeDAO;

    @Override
    public void init() {
        this.CommandeDAO=DAOFactory.getInstance().getCommandeDao();
      }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long LivreurId = (Long) session.getAttribute("LivreurId");

        if (LivreurId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            // Récupération des commandes
            List<Commande> commandes = CommandeDAO.getALLCommandes();
            request.setAttribute("commandes", commandes);
            System.out.println("Nombre de commandes récupérées : " + commandes.size());
            for (Commande c : commandes) {
                System.out.println(c);
            }
            // Redirection vers la vue
            request.getRequestDispatcher("/Livreur/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de la récupération des commandes.");
            request.getRequestDispatcher("/Livreur/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateEtat".equals(action)) {
            try {
                long idCommande = Long.parseLong(request.getParameter("idCommande"));
                String nouvelEtat = request.getParameter("nouvelEtat");

                // Mise à jour de l'état de la commande
                try {
					CommandeDAO.updateCommandeEtat(idCommande, nouvelEtat);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                // Redirection vers la liste des commandes
                response.sendRedirect("LivreurCommande");
            } catch (NumberFormatException e) {
                throw new ServletException("ID de commande invalide.", e);
            } catch (DAOException e) {
                throw new ServletException("Erreur lors de la mise à jour de l'état de la commande.", e);
            } 
        }
    }
}
