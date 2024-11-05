package com.JAVA.DAO;

import com.JAVA.Beans.ClientBean;

import  com.JAVA.utils.DAOException;
public interface ClientDAO {
    void create(ClientBean client) throws DAOException;
    ClientBean getClientByEmail(String email) throws DAOException;
}