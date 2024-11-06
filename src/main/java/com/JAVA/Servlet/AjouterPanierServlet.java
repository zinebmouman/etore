package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.JAVA.Beans.Produit;


import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AjouterPanierServlet
 */
@WebServlet("/AjouterPanierServlet")
public class AjouterPanierServlet extends HttpServlet {
	

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        long idProduit = Integer.parseInt(request.getParameter("id_Produit"));
	        HttpSession session = request.getSession();
	        
	        List<Produit> panier = (List<Produit>) session.getAttribute("panier");
	        if (panier == null) {
	            panier = new ArrayList<>();
	            session.setAttribute("panier", panier);
	        }
	        
	        // Logique pour récupérer le produit depuis la base de données (à ajouter)
	        Produit produit = new Produit(); // remplacer avec récupération depuis DAO
	        produit.setIdProduit(idProduit);
	        
	        panier.add(produit);
	        response.sendRedirect("panier.jsp");
	    }
	}
