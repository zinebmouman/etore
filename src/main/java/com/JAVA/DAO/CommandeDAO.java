package com.JAVA.DAO;

import com.JAVA.Beans.Commande;
import com.JAVA.Beans.PanierBean;
import com.JAVA.utils.DAOException;

import java.sql.*;
import java.util.List;



import com.JAVA.Beans.Commande;
import java.util.List;

public interface CommandeDAO {
	void ajouterCommande(Commande commande ) throws SQLException ;
	void modifierEtatCommande(Long idCommande, String etat)throws SQLException;
    List<Commande> getCommandesByClientId(Long clientId);
    List<Commande> getALLCommandes() throws SQLException; 
    void updateCommandeEtat(long idCommande, String nouvelEtat) throws SQLException;
}

