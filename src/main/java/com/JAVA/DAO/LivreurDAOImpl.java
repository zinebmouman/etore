package com.JAVA.DAO;

import com.JAVA.Beans.Livreur;
import com.JAVA.Beans.User;
import com.JAVA.utils.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.sql.Statement;
/**
 * LivreurDAOImpl.java
 * Cette classe fournit les opérations CRUD pour la table livreur dans la base de données.
 * Elle utilise DAOFactory pour obtenir les connexions à la base de données.
 */
public class LivreurDAOImpl implements LivreurDAO {
    private static final String INSERT_LIVREUR_SQL = "INSERT INTO livreur (nom, statut, localisation) VALUES (?, ?, ?);";
    private static final String SELECT_LIVREUR_BY_ID = "SELECT * FROM livreur WHERE id_livreur = ?;";
    private static final String SELECT_ALL_LIVREURS = "SELECT * FROM livreur;";
    private static final String DELETE_LIVREUR_BY_ID = "DELETE FROM livreur WHERE id_livreur = ?;";
    private static final String UPDATE_LIVREUR = "UPDATE livreur SET nom = ?, statut = ?, localisation = ? WHERE id_livreur = ?;";
    private static final String AUTHENTIFY_LIVREUR = "SELECT * FROM livreur WHERE nom = ? AND statut = ?;";

    private DAOFactory daoFactory;

    public LivreurDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Boolean add(Livreur livreur) throws SQLException {
        String INSERT_LIVREUR_SQL = "INSERT INTO livreur (nom, statut, localisation) VALUES (?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_LIVREUR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, livreur.getNom());
            statement.setString(2, livreur.getStatut());
            statement.setString(3, livreur.getLocalisation());

            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        livreur.setIdLivreur(generatedKeys.getLong(1)); // Récupérer l'ID généré
                    }
                }
            }
            return affectedRows > 0;
        }
    }

    
    public Boolean addUser(User user) throws SQLException {
        String INSERT_USER_SQL = "INSERT INTO user (email, 	password, type, user_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
             
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getType());
            statement.setFloat(4, user.getUserId()); // L’ID du livreur sera utilisé ici
            return statement.executeUpdate() > 0;
        }
    }


    @Override
    public Boolean update(Livreur livreur) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIVREUR)) {
             
            statement.setString(1, livreur.getNom());
            statement.setString(2, livreur.getStatut());
            statement.setString(3, livreur.getLocalisation());
            statement.setLong(4, livreur.getIdLivreur());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Boolean deleteByID(Long livreurId) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIVREUR_BY_ID)) {
             
            statement.setLong(1, livreurId);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Livreur authentifier(String nom, String statut) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(AUTHENTIFY_LIVREUR)) {
             
            statement.setString(1, nom);
            statement.setString(2, statut);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return map(resultSet);
            }
        }
        return null;
    }

    @Override
    public Livreur getOneById(Long livreurId) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIVREUR_BY_ID)) {
             
            statement.setLong(1, livreurId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return map(resultSet);
            }
        }
        return null;
    }

    @Override
    public Set<Livreur> getAll() throws SQLException {
        Set<Livreur> livreurs = new HashSet<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_LIVREURS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                livreurs.add(map(resultSet));
            }
        }
        return livreurs;
    }

    // Mapper les données SQL vers un objet Livreur
    private Livreur map(ResultSet resultSet) throws SQLException {
        Livreur livreur = new Livreur();
        livreur.setIdLivreur(resultSet.getLong("id_livreur"));
        livreur.setNom(resultSet.getString("nom"));
        livreur.setStatut(resultSet.getString("statut"));
        livreur.setLocalisation(resultSet.getString("localisation"));
        return livreur;
    }
}
