package com.JAVA.DAO;

import com.JAVA.Beans.PanierBean;

import java.sql.SQLException;
import java.util.List;

public interface PanierDAO {
    void ajouterProduit(PanierBean panier);
    void modifierQuantite(long clientId ,int panierId, int quantite)throws SQLException;
    void supprimerProduit(long clientId ,int panierId)throws SQLException;
    List<PanierBean> getProduitsPanier(long clientId);
    List<PanierBean> getProduitsPanierCL(long clientId) throws SQLException;
	void viderPanier(Long clientId) throws SQLException;
}

