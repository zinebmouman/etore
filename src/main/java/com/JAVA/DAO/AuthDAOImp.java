package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.JAVA.Beans.User;
import com.JAVA.utils.DAOFactory;

public class AuthDAOImp implements AuthDAO {

    private final Connection connection;

    public AuthDAOImp(DAOFactory daoFactory) throws SQLException {
        this.connection = daoFactory.getConnection();
    }

    @Override
    public User authenticate(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        (long) rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("type")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
