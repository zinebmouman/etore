package com.JAVA.Servlet;

import com.JAVA.Beans.Commande;
import com.JAVA.DAO.CommandeDAO;
import com.JAVA.DAO.CommandeDAOImp;
import com.JAVA.DAO.ClientDAO;
import com.JAVA.DAO.ClientDaoImpl;
import com.JAVA.utils.DAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {
    private DAOFactory daoFactory;

    @Override
    public void init() {
        daoFactory = DAOFactory.getInstance(); // Initialiser DAOFactory
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listeCommandes".equals(action)) {
            long idCommerce;

            try {
                idCommerce = Long.parseLong(request.getParameter("id_commerce")); // Récupérer l'id du commerce depuis la requête
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du commerce est invalide.");
                return;
            }

            CommandeDAO commandeDAO = new CommandeDAOImp(daoFactory);
            ClientDAO clientDAO = new ClientDaoImpl(daoFactory);

            try {
                // Récupérer la liste des commandes pour le commerce
                List<Commande> commandes = commandeDAO.getAllByCommerce(idCommerce);
                request.setAttribute("commandes", commandes); // Ajouter la liste des commandes à la requête

                // Transmettre la requête à la JSP pour l'affichage
                RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/ListeCommandeCommerce.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue lors de la récupération des commandes : " + e.getMessage());
            }
        }
        // Vous pouvez ajouter d'autres actions ici si nécessaire
    }
}
