package com.JAVA.Servlet;

import com.JAVA.DAO.LivreurDAO;
import com.JAVA.utils.DAOFactory;
import com.JAVA.Beans.Livreur;
import com.JAVA.Beans.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Gererlivreur.java
 * This servlet acts as a controller for managing 'Livreur' records, handling all requests related to 'Livreur' CRUD operations.
 */

@WebServlet("/Gererlivreur")
public class Gererlivreur extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LivreurDAO livreurDAO;

    @Override
    public void init() {
        livreurDAO = DAOFactory.getInstance().getLivreurDao();
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

        try {
            switch (action) {
                case "list":
                    listLivreurs(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "insert":
                    insertLivreur(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateLivreur(request, response);
                    break;
                case "delete":
                    deleteLivreur(request, response);
                    break;
                default:
                    listLivreurs(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listLivreurs(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Set<Livreur> livreurs = livreurDAO.getAll();
        request.setAttribute("livreurs", livreurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/views/ListeLivreur.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/views/AjouterLivreur.jsp");
        dispatcher.forward(request, response);
    }

    private void insertLivreur(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Récupère les informations pour le livreur
        String nom = request.getParameter("nom");
        String statut = request.getParameter("statut");
        String localisation = request.getParameter("localisation");

        // Récupère les informations pour l'utilisateur
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int type = Integer.parseInt(request.getParameter("type"));

        // Crée un nouvel objet Livreur
        Livreur nouveauLivreur = new Livreur();
        nouveauLivreur.setNom(nom);
        nouveauLivreur.setStatut(statut);
        nouveauLivreur.setLocalisation(localisation);

        // Insertion du livreur et récupération de son ID
        boolean livreurAdded = livreurDAO.add(nouveauLivreur);

        // Si l'insertion du livreur réussit, insère également dans la table user
        if (livreurAdded) {
            // Récupère l'ID du livreur nouvellement ajouté
            Long livreurId = nouveauLivreur.getIdLivreur(); // Cela devrait être déjà mis à jour dans la méthode add
            
            // Crée un nouvel utilisateur et utilise l'ID du livreur comme user_id
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setType(type);
            newUser.setUserId(livreurId); // Assigner l'ID du livreur à userId

            // Insère l'utilisateur dans la base de données
            livreurDAO.addUser(newUser);
        }

        response.sendRedirect("Gererlivreur?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Livreur livreur = livreurDAO.getOneById(id);
        request.setAttribute("livreur", livreur);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/views/ModifierLivreur.jsp");
        dispatcher.forward(request, response);
    }

    protected void updateLivreur(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        Long idLivreur = Long.parseLong(request.getParameter("idLivreur"));
        String nom = request.getParameter("nom");
        String statut = request.getParameter("statut");
        String localisation = request.getParameter("localisation");

        // Créer un objet Livreur avec les données du formulaire
        Livreur livreur = new Livreur(idLivreur, nom, statut, localisation);
        
        // Mettre à jour le livreur dans la base de données
        livreurDAO.update(livreur);

        // Redirection vers la liste des livreurs
        response.sendRedirect(request.getContextPath() + "/Gererlivreur?action=list");
    }



    private void deleteLivreur(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        livreurDAO.deleteByID(id);
        response.sendRedirect("Gererlivreur?action=list");
    }
}
