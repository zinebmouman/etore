package com.JAVA.Servlet;

import com.JAVA.DAO.ProduitDAO;
import com.JAVA.utils.DAOFactory;
import com.JAVA.Beans.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@WebServlet("/GererProduit")
public class GererProduit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProduitDAO produitDAO;

    @Override
    public void init() {
        produitDAO = DAOFactory.getInstance().getProduitDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idCommerceParam = request.getParameter("id_commerce");

        // Vérification des paramètres
        if (idCommerceParam == null || idCommerceParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre id_commerce est manquant dans l'URL.");
            return;
        }

        int idCommerce;
        try {
            idCommerce = Integer.parseInt(idCommerceParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id_commerce doit être un entier.");
            return;
        }

        request.setAttribute("id_commerce", idCommerce);

        try {
            if (action == null || action.isEmpty()) {
                action = "list"; // Default action
            }
            switch (action) {
                case "list":
                    listProduits(request, response, idCommerce);
                    break;
                case "add":
                    showAddForm(request, response, idCommerce);
                    break;
                case "insert":
                    insertProduit(request, response, idCommerce);
                    break;
                case "edit":
                    showEditForm(request, response, idCommerce);
                    break;
                case "update":
                    updateProduit(request, response, idCommerce);
                    break;
                case "delete":
                    deleteProduit(request, response, idCommerce);
                    break;
                default:
                    listProduits(request, response, idCommerce);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la gestion de l'action : " + action, e);
        }
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response, int idCommerce)
            throws SQLException, IOException, ServletException {
        Set<Produit> produits = produitDAO.getAll(idCommerce);
        request.setAttribute("produits", produits);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/listeProduit.jsp");
        dispatcher.forward(request, response);
    }
    
    

    private void showAddForm(HttpServletRequest request, HttpServletResponse response, int idCommerce)
            throws ServletException, IOException {
        request.setAttribute("id_commerce", idCommerce);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/AjouterProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response, int idCommerce)
            throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        Double prix;

        try {
            prix = Double.parseDouble(request.getParameter("prix"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prix invalide.");
            return;
        }

        Produit nouveauProduit = new Produit();
        nouveauProduit.setNom(nom);
        nouveauProduit.setDescription(description);
        nouveauProduit.setPrix(prix);
        nouveauProduit.setIdCommerce((long) idCommerce); // Associe le produit au commerce

        produitDAO.add(nouveauProduit);

        response.sendRedirect(request.getContextPath() + "/GererProduit?action=list&id_commerce=" + idCommerce);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, int idCommerce)
            throws SQLException, ServletException, IOException {
        Long idProduit = Long.parseLong(request.getParameter("id_produit"));
        Produit produit = produitDAO.getOneById(idProduit);

        if (produit == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Produit non trouvé.");
            return;
        }

        request.setAttribute("produit", produit);
        request.setAttribute("id_commerce", idCommerce);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/ModifierProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response, int idCommerce)
            throws SQLException, IOException {
        Long idProduit = Long.parseLong(request.getParameter("id_produit"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        Double prix;

        try {
            prix = Double.parseDouble(request.getParameter("prix"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Prix invalide.");
            return;
        }

        Produit produit = new Produit();
        produit.setIdProduit(idProduit);
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setIdCommerce((long) idCommerce); // Associe à nouveau le produit au commerce

        produitDAO.update(produit);

        response.sendRedirect(request.getContextPath() + "/GererProduit?action=list&id_commerce=" + idCommerce);
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response, int idCommerce)
            throws SQLException, IOException {
        Long idProduit = Long.parseLong(request.getParameter("id_produit"));
        produitDAO.deleteByID(idProduit);

        response.sendRedirect(request.getContextPath() + "/GererProduit?action=list&id_commerce=" + idCommerce);
    }
}
