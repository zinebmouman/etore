package com.JAVA.Servlet;

import com.JAVA.DAO.ProduitDAO;
import com.JAVA.Beans.Produit;
import com.JAVA.utils.DAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GererProduit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class GererProduit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProduitDAO produitDAO;
    private static final String UPLOAD_DIRECTORY = "C:/uploads"; // Chemin où stocker les images

    @Override
    public void init() {
        produitDAO = DAOFactory.getInstance().getProduitDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idCommerceStr = request.getParameter("id_commerce");
    	long idCommerce = Long.parseLong(idCommerceStr);
        try {
            switch (action) {
                case "add":
                    addProduit(request, response);
                    break;
                case "update":
                    updateProduit(request, response);
                    break;
                case "delete":
                    deleteProduit(request, response);
                    break;
                default:
                    response.sendRedirect("GererProduit?action=list&id_commerce="+ idCommerce);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors du traitement du produit", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idCommerceStr = request.getParameter("id_commerce");
    	long idCommerce = Long.parseLong(idCommerceStr);
        try {
            switch (action) {
                case "list":
                    listProduits(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "showAddForm":
                    showAddForm(request, response);
                    break;
                default:
                    response.sendRedirect("GererProduit?action=list&id_commerce="+ idCommerce );
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des produits", e);
        }
    }

    private void addProduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        // Récupérer les paramètres
    	
        String nom = request.getParameter("nom");
        String prixStr = request.getParameter("prix");
        String description = request.getParameter("description");
        String idCommerceStr = request.getParameter("id_commerce");

        // Gestion de l'image
        Part filePart = request.getPart("image"); // Récupérer l'image uploadée
        String fileName = extractFileName(filePart);
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;

        // Sauvegarder le fichier sur le disque
        saveUploadedFile(filePart, filePath);

        // Convertir les chaînes en types appropriés
        double prix = Double.parseDouble(prixStr);
        long idCommerce = Long.parseLong(idCommerceStr);
        System.out.println("Mise à jour d'un produit : ");
        System.out.println("Nom : " + nom);
        System.out.println("Prix : " + prix);
        System.out.println("Description : " + description);
        System.out.println("ID Commerce : " + idCommerce);
        System.out.println("Image : " + fileName);
        // Créer le produit
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setPrix(prix);
        produit.setDescription(description);
        produit.setIdCommerce(idCommerce);
        produit.setImage(fileName); // Chemin du fichier enregistré

        // Ajouter le produit
        produitDAO.add(produit);

        response.sendRedirect("GererProduit?action=list&id_commerce="+ idCommerce);
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String idProduitStr = request.getParameter("id_produit");
        String nom = request.getParameter("nom");
        String prixStr = request.getParameter("prix");
        String description = request.getParameter("description");
        String idCommerceStr = request.getParameter("id_commerce");

        // Gestion de l'image
        Part filePart = request.getPart("image");
        String fileName = extractFileName(filePart);
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;

        // Sauvegarder le fichier sur le disque si une nouvelle image est uploadée
        if (filePart != null && filePart.getSize() > 0) {
            saveUploadedFile(filePart, filePath);
        }

        // Convertir les chaînes en types appropriés
        long idProduit = Long.parseLong(idProduitStr);
        double prix = Double.parseDouble(prixStr);
        long idCommerce = Long.parseLong(idCommerceStr);

        System.out.println("Mise à jour d'un produit : ");
        System.out.println("Nom : " + nom);
        System.out.println("Prix : " + prix);
        System.out.println("Description : " + description);
        System.out.println("ID Commerce : " + idCommerce);
        System.out.println("ID idProduit : " + idProduit);
        System.out.println("Image : " + fileName);
        
        // Mettre à jour le produit
        Produit produit = new Produit();
        produit.setIdProduit(idProduit);
        produit.setNom(nom);
        produit.setPrix(prix);
        produit.setDescription(description);
        produit.setIdCommerce(idCommerce);
        produit.setImage(fileName);

        produitDAO.update(produit);

        response.sendRedirect("GererProduit?action=list&id_commerce="+ idCommerce );
    }

    private void saveUploadedFile(Part filePart, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Créer les dossiers si nécessaire
        try (FileOutputStream fos = new FileOutputStream(file);
             InputStream is = filePart.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	String idCommerceStr = request.getParameter("id_commerce");
    	long idCommerce = Long.parseLong(idCommerceStr);
        long idProduit = Long.parseLong(request.getParameter("id_produit"));

        System.out.println("ID Commerce : " + idCommerce);
        System.out.println("ID idProduit : " + idProduit);
        produitDAO.deleteByID(idProduit);
        response.sendRedirect("GererProduit?action=list&id_commerce=" + idCommerce);
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int idCommerce = Integer.parseInt(request.getParameter("id_commerce"));
        List<Produit> produits = new ArrayList<>(produitDAO.getAll(idCommerce));
        request.setAttribute("produits", produits);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/listeProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        long idProduit = Long.parseLong(request.getParameter("id_produit"));
        Produit produit = produitDAO.getOneById(idProduit);
        request.setAttribute("produit", produit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/ModifierProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/commerce/views/AjouterProduit.jsp");
        dispatcher.forward(request, response);
    }
}
