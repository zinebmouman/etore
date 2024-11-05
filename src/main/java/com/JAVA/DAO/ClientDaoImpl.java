package com.JAVA.DAO;

import com.JAVA.Beans.ClientBean;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoImpl implements ClientDAO {

    private DAOFactory daoFactory;

    public ClientDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static ClientBean map(ResultSet resultSet) throws SQLException {
        ClientBean clientBean = new ClientBean();
        clientBean.setId(resultSet.getLong("id"));
        clientBean.setNom(resultSet.getString("nom"));
        clientBean.setAdress(resultSet.getString("adress"));
        clientBean.setEmail(resultSet.getString("email"));
        clientBean.setContact(resultSet.getString("contact")); // Change to String
        clientBean.setPasswrd(resultSet.getString("passwrd"));
        return clientBean;
    }

    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }

    @Override
    public void create(ClientBean client) throws DAOException {
        String sql = "INSERT INTO client (nom, adresse, contact, email, passwrd) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAdress());
            statement.setString(3, client.getContact()); // Change to String
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getPasswrd());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la création du client", e);
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
