package com.JAVA.Servlet;

import com.JAVA.Beans.Produit;
import com.JAVA.DAO.ProduitDAO;
import com.JAVA.utils.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listeProduits")
public class ListeProduitsServlet extends HttpServlet {
    private ProduitDAO produitDAO;

    @Override
    public void init() throws ServletException {
        this.produitDAO = DAOFactory.getInstance().getProduitDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la liste des produits
        List<Produit> produits = produitDAO.getAllProduits();
        
        // Placer la liste des produits dans l'objet request
        request.setAttribute("produits", produits);
        
        // Transférer la requête à la JSP pour affichage
        request.getRequestDispatcher("/Client/views/index.jsp").forward(request, response);
    }
}
