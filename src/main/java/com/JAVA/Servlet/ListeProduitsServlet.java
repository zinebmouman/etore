package com.JAVA.Servlet;

import com.JAVA.Beans.Produit;
import com.JAVA.DAO.ProduitDAO;
import com.JAVA.utils.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
    	 HttpSession session = request.getSession();

         // Récupérer l'ID du client depuis la session
         Long clientId = (Long) session.getAttribute("clientId");
         String page = request.getParameter("page");
    	try {
            List<Produit> produits = produitDAO.getAllProduits();
            request.setAttribute("produits", produits);
            if ("home".equals(page)) {
                request.getRequestDispatcher("/Client/views/Home.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/Client/views/index.jsp?clientId=" + clientId).forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits.", e);
        }
           
    }
}
