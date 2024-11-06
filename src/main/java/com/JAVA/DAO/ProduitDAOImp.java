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
    private static final String INSERT_PRODUIT_SQL = "INSERT INTO produit (nom, prix, description, id_commerce) VALUES (?, ?, ?, ?);";
    private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM produit WHERE id_produit = ?;";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produit WHERE id_commerce = ?;";
    private static final String DELETE_PRODUIT_BY_ID = "DELETE FROM produit WHERE id_produit = ?;";
    private static final String UPDATE_PRODUIT = "UPDATE produit SET nom = ?, prix = ?, description = ?, id_commerce = ? WHERE id_produit = ?;";

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
            statement.setLong(5, produit.getIdProduit());   // Assure que l'ID produit est mis à jour
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteByID(Long produitId) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUIT_BY_ID)) {
             
            statement.setLong(1, produitId);
            statement.executeUpdate();
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
    public Set<Produit> getAll(int idCommerce) throws SQLException {
        Set<Produit> produits = new HashSet<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUITS)) {
             
            statement.setInt(1, idCommerce);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produits.add(map(resultSet));
            }
        }
        return produits;
    }

    @Override
    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(resultSet.getLong("id_produit"));
                produit.setNom(resultSet.getString("nom"));
                produit.setPrix(resultSet.getDouble("prix"));
                produit.setIdCommerce(resultSet.getLong("id_commerce"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Erreur lors de la récupération de la liste des produits", e);
        }

        return produits;
    }
    private Produit map(ResultSet resultSet) throws SQLException {
        Produit produit = new Produit();
        produit.setIdProduit(resultSet.getLong("id_produit"));
        produit.setNom(resultSet.getString("nom"));
        produit.setPrix(resultSet.getDouble("prix"));
        produit.setDescription(resultSet.getString("description"));
        produit.setIdCommerce(resultSet.getLong("id_commerce")); // Associe le produit à son commerce
        return produit;
    }
}
