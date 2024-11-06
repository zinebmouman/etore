package com.JAVA.DAO;

import com.JAVA.Beans.PanierBean;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanierDAO {
    private DAOFactory daoFactory;

    public PanierDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void creerPanier(PanierBean panier) throws DAOException {
        String sql = "INSERT INTO Panier (utilisateur_id, produits) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, panier.getUtilisateurId());
            statement.setString(2, panier.getProduitsJSON());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la création du panier", e);
        }
    }

    public PanierBean obtenirPanierParUtilisateur(int utilisateurId) throws DAOException {
        String sql = "SELECT * FROM Panier WHERE utilisateur_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, utilisateurId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PanierBean panier = new PanierBean();
                panier.setId(resultSet.getInt("id"));
                panier.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                panier.setProduitsJSON(resultSet.getString("produits"));
                return panier;
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du panier", e);
        }
        return null;
    }
}
