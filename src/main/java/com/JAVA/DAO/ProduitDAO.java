package com.JAVA.DAO;

import com.JAVA.Beans.Produit;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ProduitDAO {
	void add(Produit produit) throws SQLException; // Modification : void pour les méthodes d'ajout
    void update(Produit produit) throws SQLException; // Modification : void pour les méthodes de mise à jour
    void deleteByID(Long produitId) throws SQLException; // Modification : void pour la méthode de suppression
    Produit getOneById(Long produitId) throws SQLException;
    List<Produit> getAll(int idCommerce) throws SQLException;
    List<Produit> getAllProduits() throws SQLException;
}
