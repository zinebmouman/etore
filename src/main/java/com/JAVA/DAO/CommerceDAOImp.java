package com.JAVA.DAO;

import com.JAVA.Beans.Commerce;
import com.JAVA.Beans.User;
import com.JAVA.utils.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommerceDAOImp implements CommerceDAO {
    private static final String INSERT_COMMERCE_SQL = "INSERT INTO commerce (nom, adresse, contact, type_commerce) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_COMMERCE_SQL = "UPDATE commerce SET nom = ?, adresse = ?, contact = ?, type_commerce = ? WHERE id_commerce = ?;";
    private static final String DELETE_COMMERCE_BY_ID_SQL = "DELETE FROM commerce WHERE id_commerce = ?;";
    private static final String SELECT_COMMERCE_BY_ID_SQL = "SELECT * FROM commerce WHERE id_commerce = ?;";
    private static final String SELECT_ALL_COMMERCE_SQL = "SELECT * FROM commerce;";

    private DAOFactory daoFactory;

    public CommerceDAOImp(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override 
    public void addCommerce(Commerce commerce, String email, String password) throws SQLException {
        String INSERT_COMMERCE_SQL = "INSERT INTO commerce (nom, adresse, contact, type_commerce) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement commerceStmt = connection.prepareStatement(INSERT_COMMERCE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            // Set the Commerce parameters
            commerceStmt.setString(1, commerce.getNom());
            commerceStmt.setString(2, commerce.getAdresse());
            commerceStmt.setString(3, commerce.getContact());
            commerceStmt.setString(4, commerce.getType_commerce());
            
            int affectedRows = commerceStmt.executeUpdate();
            
            // Retrieve the generated id_commerce
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = commerceStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idCommerce = generatedKeys.getLong(1);
                        commerce.setId_commerce(idCommerce);
                        
                        // Now add the corresponding User entry
                        addUserForCommerce(idCommerce, email, password);
                    }
                }
            }
        }
    }

    // Helper method to add a User entry for the new Commerce
    public void addUserForCommerce(long idCommerce, String email, String password) throws SQLException {
        String INSERT_USER_SQL = "INSERT INTO user (email, password, type, user_id) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement userStmt = connection.prepareStatement(INSERT_USER_SQL)) {
            
            userStmt.setString(1, email);                 // Use provided email from form
            userStmt.setString(2, password);              // Use provided password from form
            userStmt.setInt(3, 2);                        // Assuming `2` represents the user type for `Commerce`
            userStmt.setLong(4, idCommerce);              // Use id_commerce as user_id in the user table
            
            userStmt.executeUpdate();
        }
    }

    @Override
    public void updateCommerce(Commerce commerce) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COMMERCE_SQL)) {
             
            statement.setString(1, commerce.getNom());
            statement.setString(2, commerce.getAdresse());
            statement.setString(3, commerce.getContact());
            statement.setString(4, commerce.getType_commerce());
            statement.setLong(5, commerce.getId_commerce());
            statement.executeUpdate();
        }
    }

    @Override
    public Boolean deleteByID(Long id_commerce) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMMERCE_BY_ID_SQL)) {
            
            statement.setLong(1, id_commerce); // Associe l'ID du commerce à la requête
            int rowsAffected = statement.executeUpdate(); // Exécute la mise à jour
            return rowsAffected > 0; // Retourne vrai si une ligne a été supprimée
        }
    }



    @Override
    public Commerce getById(Long id) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COMMERCE_BY_ID_SQL)) {
             
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return map(resultSet);
            }
        }
        return null;
    }

    @Override
    public List<Commerce> getAll() throws SQLException {
        List<Commerce> commerces = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COMMERCE_SQL);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                commerces.add(map(resultSet));
            }
        }
        return commerces;
    }

    // Mapper les données SQL vers un objet Commerce
    private Commerce map(ResultSet resultSet) throws SQLException {
        // Créez l'objet Commerce en utilisant le constructeur avec tous les attributs.
        return new Commerce(
            resultSet.getLong("id_commerce"),
            resultSet.getString("nom"),
            resultSet.getString("adresse"),
            resultSet.getString("contact"),
            resultSet.getString("type_commerce")
        );
    }

}
