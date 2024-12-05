package com.JAVA.Servlet;

import com.JAVA.DAO.CommerceDAO;
import com.JAVA.utils.DAOFactory;
import com.JAVA.Beans.Commerce;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Gerercommercier")
public class Gerercommercier extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommerceDAO commerceDAO;

    @Override
    public void init() {
        commerceDAO = DAOFactory.getInstance().getCommerceDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addCommerce(request, response);
        } else if ("update".equals(action)) {
        	try {
                updateCommerce(request, response);
            } catch (SQLException e) {
                throw new ServletException("Erreur lors de la mise à jour du commerce", e);
            }
        } else if ("delete".equals(action)) {
            try {
                deleteCommerce(request, response);
            } catch (SQLException e) {
                throw new ServletException("Erreur lors de la suppression du commerce", e);
            }
        }
        }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            listCommerces(request, response);
        } else if ("edit".equals(action)) {
        	try {
                showEditForm(request, response);
            } catch (SQLException e) {
                throw new ServletException("Erreur lors de la récupération des informations du commerce", e);
            }
        }
        else if ("showaddform".equals(action)) {
        	showAddForm(request, response);
        }
    }

    private void addCommerce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String contact = request.getParameter("contact");
        String type_commerce = request.getParameter("type_commerce");
        String email = request.getParameter("email");      // Retrieve email from the form
        String password = request.getParameter("password"); // Retrieve password from the form

        // Create the Commerce object
        Commerce commerce = new Commerce(null, nom, adresse, contact, type_commerce);

        try {
            // This will add both the Commerce and User entries
            commerceDAO.addCommerce(commerce, email, password);
            
            response.sendRedirect("Gerercommercier?action=list");
        } catch (SQLException e) {
            throw new ServletException("Error adding commerce", e);
        }
    }




    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/views/Ajoutercommercier.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCommerce(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        String idParam = request.getParameter("id");
        Long id_commerce = Long.parseLong(idParam);
        
        // Vérifie si le commerce a des commandes avant de supprimer
        boolean hasOrders = commerceDAO.hasOrders(id_commerce);
        
        if (hasOrders) {
            // Redirige avec un message indiquant que le commerce a des commandes
            request.setAttribute("errorMessage", "Ce commerce a des commandes et ne peut pas être supprimé.");
            listCommerces(request, response);
            return;
        }
        
        // Si aucune commande n'est associée, procéder à la suppression
        boolean isDeleted = commerceDAO.deleteByID(id_commerce);
        
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/Gerercommercier?action=list");
        } else {
            throw new ServletException("Erreur lors de la suppression du commerce. Aucune ligne n'a été supprimée.");
        }
    }







    private void listCommerces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Commerce> commerces = commerceDAO.getAll();
            // Vérifie si chaque commerce a des commandes
            for (Commerce commerce : commerces) {
                boolean hasOrders = commerceDAO.hasOrders(commerce.getId_commerce());  // Vérifie si ce commerce a des commandes
                commerce.setHasOrders(hasOrders);  // Ajoute cette information à l'objet commerce (création de la variable hasOrders dans la classe Commerce)
            }
            
            request.setAttribute("commerces", commerces);
            request.getRequestDispatcher("/admin/views/listeCommerces.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving commerces", e);
        }
    }

   

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String idParam = request.getParameter("id");

        // Log the idParam to check if it was received
        System.out.println("ID du commerce reçu : " + idParam);

        if (idParam == null || idParam.isEmpty()) {
            throw new ServletException("ID du commerce manquant ou invalide.");
        }

        Long id_commerce;
        try {
            id_commerce = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            throw new ServletException("ID du commerce non valide : " + idParam, e);
        }

        Commerce commerce = commerceDAO.getById(id_commerce);
        if (commerce == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Commerce non trouvé pour ID : " + id_commerce);
            return;
        }

        request.setAttribute("commerce", commerce);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/views/Modifiercommerce.jsp");
        dispatcher.forward(request, response);
    }

    protected void updateCommerce(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Long id_commerce = Long.parseLong(request.getParameter("id_commerce"));
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String contact = request.getParameter("contact");
        String type_commerce = request.getParameter("type_commerce");

        Commerce commerce = new Commerce(id_commerce, nom, adresse, contact, type_commerce);

        try {
            commerceDAO.updateCommerce(commerce);
            response.sendRedirect(request.getContextPath() + "/Gerercommercier?action=list");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise à jour du commerce", e);
        }
    }


    

    @Override
    public void destroy() {
        // Note: In this version, we don't explicitly close the connection as it's managed by DAOFactory
    }
}