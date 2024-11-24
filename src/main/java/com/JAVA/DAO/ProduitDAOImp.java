package com.JAVA.DAO;

import com.JAVA.Beans.Produit;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProduitDAOImp implements ProduitDAO {
    private static final String INSERT_PRODUIT_SQL = "INSERT INTO produit (nom, prix, description, id_commerce, image) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM produit WHERE id_produit = ?;";
    private static final String SELECT_ALL_PRODUITS_commerce = "SELECT * FROM produit WHERE id_commerce = ?;";
    private static final String DELETE_PRODUIT_BY_ID = "DELETE FROM produit WHERE id_produit = ?;";
    private static final String UPDATE_PRODUIT = "UPDATE produit SET nom = ?, prix = ?, description = ?, id_commerce = ?, image = ? WHERE id_produit = ?;";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produit";
    private DAOFactory daoFactory;

    public ProduitDAOImp(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Produit produit) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUIT_SQL)) {
             
            statement.setString(1, produit.getNom());
            statement.setDouble(2, produit.getPrix());
            statement.setString(3, produit.getDescription());
            statement.setLong(4, produit.getIdCommerce()); // Ajout de l'ID du commerce
            statement.setString(5, produit.getimage());  // Ajout de l'image
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Produit produit) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUIT)) {
             
            statement.setString(1, produit.getNom());
            statement.setDouble(2, produit.getPrix());
            statement.setString(3, produit.getDescription());
            statement.setLong(4, produit.getIdCommerce()); // Assure que l'ID commerce est mis à jour
            statement.setString(5, produit.getimage());  // Mise à jour de l'image
            statement.setLong(6, produit.getIdProduit());   // Assure que l'ID produit est mis à jour
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteByID(Long produitId) throws SQLException {
    	try (Connection connection = daoFactory.getConnection();
    		     PreparedStatement statement = connection.prepareStatement(DELETE_PRODUIT_BY_ID)) {
    		    
    		    statement.setLong(1, produitId);
    		    int rowsAffected = statement.executeUpdate();
    		    System.out.println("Nombre de lignes supprimées : " + rowsAffected);
    		} catch (SQLException e) {
    			if (e.getSQLState().equals("23000")) { // Code pour violation de clé étrangère
    		        System.err.println("Violation de clé étrangère : " + e.getMessage());
    		    } else {
    		        throw e;
    		    }
    		}

    }

    @Override
    public Produit getOneById(Long produitId) throws SQLException {
        Produit produit = null;
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUIT_BY_ID)) {
             
            statement.setLong(1, produitId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                produit = map(resultSet);
            }
        }
        return produit;
    }

    @Override
    public List<Produit> getAll(int idCommerce) throws SQLException {
        List<Produit> produits = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUITS_commerce)) {
             
            statement.setInt(1, idCommerce);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produits.add(map(resultSet));
            }
        }
        return produits;
    }

    @Override
    public List<Produit> getAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUITS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                produits.add(map(resultSet));
            }
        }
        return produits;
    }

    private Produit map(ResultSet resultSet) throws SQLException {
        Produit produit = new Produit();
        produit.setIdProduit(resultSet.getLong("id_produit"));
        produit.setNom(resultSet.getString("nom"));
        produit.setPrix(resultSet.getDouble("prix"));
        produit.setDescription(resultSet.getString("description"));
        produit.setImage(resultSet.getString("image"));  // Récupération de l'image
        produit.setIdCommerce(resultSet.getLong("id_commerce")); // Associe le produit à son commerce
        return produit;
    }
}
