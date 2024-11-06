package com.JAVA.DAO;

import java.sql.SQLException;

import com.JAVA.Beans.ClientBean;
import com.JAVA.Beans.User;
import  com.JAVA.utils.DAOException;
public interface ClientDAO {
    void create(ClientBean clientBean ,String email, String password) throws DAOException ;
		
		
	
    ClientBean getClientByEmail(String email) throws DAOException;
    void addUserForClient(long id_client , String email, String password) throws SQLException;
 

}