package com.JAVA.DAO;

import com.JAVA.Beans.PanierBean;
import com.JAVA.Beans.Produit;
import com.JAVA.utils.DAOFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierDAOImpl implements PanierDAO {

    private DAOFactory daoFactory;

    public PanierDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterProduit(PanierBean panier) {
        String query = "INSERT INTO panier (client_id, produit_id, quantite) VALUES (?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, panier.getClientId());
            statement.setInt(2, panier.getProduitId());
            statement.setInt(3, panier.getQuantite()); 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void modifierQuantite(long clientId, int produitId, int quantite) throws SQLException {
        String sql = "UPDATE panier SET quantite = ? WHERE client_id = ? AND produit_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantite);
            statement.setLong(2, clientId);
            statement.setInt(3, produitId);
            statement.executeUpdate();
        }
    }

    @Override
    public void supprimerProduit(long clientId, int produitId) throws SQLException {
        String sql = "DELETE FROM panier WHERE client_id = ? AND produit_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, clientId);
            stmt.setInt(2, produitId);
            stmt.executeUpdate();
        }
    }

    

    @Override
    public List<PanierBean> getProduitsPanier(long clientId) {
        List<PanierBean> produitsPanier = new ArrayList<>();
        String query = "SELECT p.*, pr.nom, pr.prix, pr.description, pr.image FROM panier p JOIN produit pr ON p.produit_id = pr.id_produit WHERE p.client_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Produit produit = new Produit(
                    resultSet.getLong("produit_id"),
                    resultSet.getString("nom"),
                    resultSet.getString("image"),
                    resultSet.getDouble("prix"),
                    resultSet.getString("description"),
                    null
                );
                
                PanierBean panier = new PanierBean(clientId, resultSet.getInt("produit_id"), resultSet.getInt("quantite"));
                panier.setProduit(produit);
                produitsPanier.add(panier);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des produits du panier : " + e.getMessage());
            e.printStackTrace();
        }
        return produitsPanier;
    }

    public List<PanierBean> getProduitsPanierCL(long clientId) throws SQLException {
        String sql = "SELECT p.id_produit AS produit_id, p.nom, p.prix, p.description, p.id_commerce, p.image, " +
                     "pa.quantite FROM produit p " +
                     "JOIN panier pa ON p.id_produit= pa.produit_id " +
                     "WHERE pa.client_id = ?";
        List<PanierBean> panierItems = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, clientId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Produit produit = new Produit();
                    produit.setIdProduit(rs.getLong("produit_id"));
                    produit.setNom(rs.getString("nom"));
                    produit.setPrix(rs.getDouble("prix"));
                    produit.setDescription(rs.getString("description"));
                    produit.setIdCommerce(rs.getLong("id_commerce"));
                    produit.setImage(rs.getString("image"));
                    
                    PanierBean panierItem = new PanierBean();
                    panierItem.setProduit(produit);
                    panierItem.setQuantite(rs.getInt("quantite"));
                    
                    panierItems.add(panierItem);
                }
            }
        }
        return panierItems;
    }


	@Override
	  public void viderPanier(Long clientId) throws SQLException {
        String sql = "DELETE FROM panier WHERE client_id = ?";
        try (Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            statement.executeUpdate();
        }
    }
}
