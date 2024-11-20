package com.JAVA.Servlet;

import com.JAVA.Beans.Commande;
import com.JAVA.Beans.PanierBean;
import com.JAVA.Beans.Produit;
import com.JAVA.DAO.CommandeDAO;
import com.JAVA.DAO.PanierDAO;
import com.JAVA.utils.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {
    private PanierDAO panierDAO;
    private CommandeDAO CommandeDAO;
    @Override
    public void init() {
        this.panierDAO = DAOFactory.getInstance().getPanierDao();
      this.CommandeDAO=DAOFactory.getInstance().getCommandeDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String clientIdParam = request.getParameter("clientId");
      
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // Récupérer l'ID du client depuis la session
        Long clientId = (Long) session.getAttribute("clientId");

        // Vérifiez si l'ID client est déjà dans la session, sinon redirigez vers la connexion
        if (clientId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
            try {
            switch (action) {
                case "ajouter":
                    String produitIdStr = request.getParameter("produitId");
                    if (produitIdStr != null && !produitIdStr.isEmpty()) {
                        int produitId = Integer.parseInt(produitIdStr);
                        panierDAO.ajouterProduit(new PanierBean(clientId, produitId, 1));
                    }
                    response.sendRedirect("listeProduits?clientId=" + clientId);
                    break;
                   
                case "afficher": 
                List<PanierBean> produitsPanier = panierDAO.getProduitsPanier(clientId);
                
                request.setAttribute("produitsPanier", produitsPanier);
                request.getRequestDispatcher("/Client/views/monPanier.jsp?clientId=" + clientId).forward(request, response);
                break;

                case "modifier":
                    if (request.getParameter("produitId") != null && request.getParameter("quantite") != null) {
                        int produitIdModifier = Integer.parseInt(request.getParameter("produitId"));
                        int quantite = Integer.parseInt(request.getParameter("quantite"));
                        panierDAO.modifierQuantite(clientId, produitIdModifier, quantite);
                    }
                    response.sendRedirect("panier?action=afficher&clientId=" + clientId);
                    break;

                case "supprimer":
                	if (request.getParameter("produitId") != null ) {
                    int produitIdSupprimer = Integer.parseInt(request.getParameter("produitId"));
                    panierDAO.supprimerProduit(clientId, produitIdSupprimer);
                    response.sendRedirect("panier?action=afficher&clientId=" + clientId);
                	}
                    break;
                	case "commander":
                           List<PanierBean> panierItems = panierDAO.getProduitsPanierCL(clientId);
                      
                        	
                             System.out.println("Client ID : " + clientId);
                             System.out.println("Produits dans le panier : " + panierItems.size());
                             for (PanierBean item : panierItems) {
                            	    if (item.getProduit() != null && item.getProduit().getIdProduit() != null) {
                            	        Long commerceId = item.getProduit().getIdCommerce();
                            	        if (commerceId != null) {
                            	            Commande commande = new Commande(
                            	                null, new Date(), "En attente", clientId, 
                            	                commerceId,
                            	                item.getProduit().getIdProduit(), 
                            	                item.getQuantite()
                            	            );
                            	            CommandeDAO.ajouterCommande(commande);
                            	        } else {
                            	            System.err.println("ID de commerce manquant pour le produit : " + item.getProduit().getIdProduit());
                            	        }
                            	    } else {
                            	        System.err.println("Produit ou ID de produit manquant dans le panier : " + item);
                            	    }
                            	}
                          panierDAO.viderPanier(clientId);
                        response.sendRedirect("panier?action=afficher&clientId=\" + clientId");
                        break;
                	
                	} 	 
          
            }  catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la gestion du panier", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);  // Redirigez tous les appels GET vers doPost
    }}
