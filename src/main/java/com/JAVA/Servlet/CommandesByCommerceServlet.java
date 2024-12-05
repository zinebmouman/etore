package com.JAVA.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.JAVA.Beans.Commande;
import com.JAVA.Beans.Produit;
import com.JAVA.DAO.CommandeDAO;
import com.JAVA.DAO.ProduitDAO;
import com.JAVA.utils.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/commandesByCommerce")
public class CommandesByCommerceServlet extends HttpServlet {
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;

    @Override
    public void init() throws ServletException {
        this.commandeDAO = DAOFactory.getInstance().getCommandeDao(); // Assurez-vous que CommandeDAO est bien initialisé.
        this.produitDAO = DAOFactory.getInstance().getProduitDao();   // Initialisez ProduitDAO via DAOFactory.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCommerceParam = request.getParameter("id_commerce");
        if (idCommerceParam == null || idCommerceParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du commerce est requis.");
            return;
        }

        try {
            long id_commerce = Long.parseLong(idCommerceParam);

            // Récupérer les commandes du commerce
            List<Commande> commandes = commandeDAO.getCommandesByCommerce(id_commerce);

            // Enrichir chaque commande avec les informations du produit correspondant
            for (Commande commande : commandes) {
                Long produitId = commande.getId_produit();
                if (produitId != null) {
                    Produit produit = produitDAO.getOneById(produitId);
                    commande.setProduit(produit); // Ajoutez un setter dans la classe Commande pour stocker le Produit
                }
            }

            // Ajouter les attributs pour la JSP
            request.setAttribute("commandes", commandes);
            request.setAttribute("id_commerce", id_commerce);

            // Redirection vers la page JSP
            request.getRequestDispatcher("/commerce/views/commandesByCommerce.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du commerce doit être un nombre valide.");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des commandes ou des produits.", e);
        }
    }
}
