package com.JAVA.DAO;

import java.sql.SQLException;
import java.util.Set;
import com.JAVA.Beans.Livreur;
import com.JAVA.Beans.User;

/**
 * Interface LivreurDAO
 * Définit les méthodes CRUD pour la gestion des livreurs dans la base de données.
 */
public interface LivreurDAO {

    /**
     * Ajoute un nouveau livreur à la base de données.
     * 
     * @param livreur L'objet Livreur à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    Boolean add(Livreur livreur) throws SQLException;
    Boolean addUser(User user) throws SQLException;

    /**
     * Met à jour les informations d'un livreur existant dans la base de données.
     * 
     * @param livreur L'objet Livreur avec les informations mises à jour.
     * @return true si la mise à jour est réussie, false sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    Boolean update(Livreur livreur) throws SQLException;

    /**
     * Supprime un livreur de la base de données par son identifiant.
     * 
     * @param livreurId L'identifiant du livreur à supprimer.
     * @return true si la suppression est réussie, false sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    Boolean deleteByID(Long livreurId) throws SQLException;

    /**
     * Authentifie un livreur par son nom et son statut.
     * 
     * @param nom Le nom du livreur.
     * @param statut Le statut du livreur.
     * @return L'objet Livreur si l'authentification réussit, null sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    Livreur authentifier(String nom, String statut) throws SQLException;

    /**
     * Récupère un livreur par son identifiant.
     * 
     * @param livreurId L'identifiant du livreur.
     * @return L'objet Livreur correspondant à l'identifiant, ou null si aucun livreur n'est trouvé.
     * @throws SQLException En cas d'erreur SQL.
     */
    Livreur getOneById(Long livreurId) throws SQLException;

    /**
     * Récupère tous les livreurs de la base de données.
     * 
     * @return Un ensemble de tous les objets Livreur de la base de données.
     * @throws SQLException En cas d'erreur SQL.
     */
    Set<Livreur> getAll() throws SQLException;
}
