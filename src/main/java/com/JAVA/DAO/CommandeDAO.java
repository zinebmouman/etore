package com.JAVA.DAO;

import com.JAVA.Beans.Commande;

import java.sql.*;
import java.util.List;

public interface CommandeDAO {
    void addCommande(Commande commande) throws SQLException;
    void updateCommande(Commande commande) throws SQLException;
    Boolean deleteById(Long id_commande) throws SQLException;
    Commande getById(Long id) throws SQLException;
    List<Commande> getAllByCommerce(Long id_commerce) throws SQLException; // Récupérer les commandes par commerce
}
