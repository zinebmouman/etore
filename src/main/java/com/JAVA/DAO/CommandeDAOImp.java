package com.JAVA.DAO;

import com.JAVA.Beans.Commande;
import com.JAVA.utils.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAOImp implements CommandeDAO {
    private static final String INSERT_COMMANDE_SQL = "INSERT INTO commande (date_commande, etat, id_client, id_commerce) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_COMMANDE_SQL = "UPDATE commande SET date_commande = ?, etat = ?, id_client = ?, id_commerce = ? WHERE id_commande = ?;";
    private static final String DELETE_COMMANDE_BY_ID_SQL = "DELETE FROM commande WHERE id_commande = ?;";
    private static final String SELECT_COMMANDE_BY_ID_SQL = "SELECT * FROM commande WHERE id_commande = ?;";
    private static final String SELECT_ALL_COMMANDE_BY_COMMERCE_SQL = "SELECT * FROM commande WHERE id_commerce = ?;";

    private DAOFactory daoFactory;

    public CommandeDAOImp(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addCommande(Commande commande) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_COMMANDE_SQL, Statement.RETURN_GENERATED_KEYS)) {
             
            statement.setDate(1, new Date(commande.getDate_commande().getTime()));
            statement.setString(2, commande.getEtat());
            statement.setLong(3, commande.getId_client());
            statement.setLong(4, commande.getId_commerce());

            statement.executeUpdate();
        }
    }

    @Override
    public void updateCommande(Commande commande) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COMMANDE_SQL)) {
             
            statement.setDate(1, new Date(commande.getDate_commande().getTime()));
            statement.setString(2, commande.getEtat());
            statement.setLong(3, commande.getId_client());
            statement.setLong(4, commande.getId_commerce());
            statement.setLong(5, commande.getId_commande());
            statement.executeUpdate();
        }
    }

    @Override
    public Boolean deleteById(Long id_commande) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMMANDE_BY_ID_SQL)) {
             
            statement.setLong(1, id_commande);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Commande getById(Long id) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COMMANDE_BY_ID_SQL)) {
             
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return map(resultSet);
            }
        }
        return null;
    }

    @Override
    public List<Commande> getAllByCommerce(Long id_commerce) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COMMANDE_BY_COMMERCE_SQL)) {
             
            statement.setLong(1, id_commerce);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commandes.add(map(resultSet));
            }
        }
        return commandes;
    }


    // Mapper les données SQL vers un objet Commande
    private Commande map(ResultSet resultSet) throws SQLException {
        return new Commande(
            resultSet.getLong("id_commande"),
            resultSet.getDate("date_commande"),
            resultSet.getString("etat"),
            resultSet.getLong("id_client"),
            resultSet.getLong("id_commerce")
        );
    }
}
