package com.JAVA.DAO;

import com.JAVA.Beans.Commerce;
import com.JAVA.Beans.User;

import java.sql.SQLException;
import java.util.List;

public interface CommerceDAO {
    void addCommerce(Commerce commerce, String email, String password) throws SQLException;
    void addUserForCommerce(long idCommerce , String email, String password) throws SQLException;
    void updateCommerce(Commerce commerce) throws SQLException;
    Boolean deleteByID(Long id) throws SQLException;
    Commerce getById(Long id) throws SQLException;
    List<Commerce> getAll() throws SQLException;
	boolean hasOrders(Long id) throws SQLException;
}
