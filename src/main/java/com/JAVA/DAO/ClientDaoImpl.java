package com.JAVA.DAO;

import com.JAVA.Beans.ClientBean;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDaoImpl implements ClientDAO {

    private DAOFactory daoFactory;

    public ClientDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static ClientBean map(ResultSet resultSet) throws SQLException {
        ClientBean clientBean = new ClientBean();
        clientBean.setId_client(resultSet.getLong("id_client"));
        clientBean.setNom(resultSet.getString("nom"));
        clientBean.setAdress(resultSet.getString("adress"));
        clientBean.setEmail(resultSet.getString("email"));
        clientBean.setContact(resultSet.getString("contact")); // Change to String
        clientBean.setpassword(resultSet.getString("password"));
        return clientBean;
    }

    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }

    
    public long create(ClientBean client, String email, String password) throws DAOException {
        String SQL = "INSERT INTO client (nom, adresse, contact, email, password) VALUES (?, ?, ?, ?, ?)";
        long clientId = -1;
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement clientStmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            clientStmt.setString(1, client.getNom());
            clientStmt.setString(2, client.getAdress());
            clientStmt.setString(3, client.getContact());
            clientStmt.setString(4, client.getEmail());
            clientStmt.setString(5, client.getpassword());

            int affectedRows = clientStmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = clientStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        clientId = generatedKeys.getLong(1);
                        client.setId_client(clientId);

                        addUserForClient(clientId, email, password);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'insertion du client", e);
     
        }
      
		return clientId;
    }
    
    

    public void addUserForClient(long id_client, String email, String password) throws SQLException {
        String INSERT_USER_SQL = "INSERT INTO user (email, password, type, user_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement userStmt = connection.prepareStatement(INSERT_USER_SQL)) {

            userStmt.setString(1, email);
            userStmt.setString(2, password);
            userStmt.setInt(3, 4); // Supposant que 4 représente le type d'utilisateur pour un client
            userStmt.setLong(4, id_client);

            userStmt.executeUpdate();
        }
    }


    @Override
    public ClientBean getClientByEmail(String email) throws DAOException {
        String sql = "SELECT * FROM client WHERE email = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du client par email", e);
        }
        return null;
    }
}
