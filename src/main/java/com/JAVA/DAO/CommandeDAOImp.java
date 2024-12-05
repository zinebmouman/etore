package com.JAVA.DAO;

import com.JAVA.Beans.Commande;
import com.JAVA.Beans.PanierBean;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommandeDAOImp implements CommandeDAO {
  
    private DAOFactory daoFactory;

    public CommandeDAOImp(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterCommande(Commande commande ) throws SQLException {
        String sql = "INSERT INTO commande (id_client ,id_produit ,quantite	,id_commerce ,date_commande,etat) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, commande.getid_client());
            statement.setLong(2, commande.getId_produit());
            statement.setInt(3, commande.getQuantite());
            statement.setLong(4, commande.getId_commerce());
            statement.setDate(5, new java.sql.Date(commande.getDate_commande().getTime()));
            statement.setString(6, commande.getEtat());

            statement.executeUpdate();
        }
    catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public List<Commande> getCommandesByClientId(Long clientId) {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande WHERE id_client = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, clientId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Commande commande = new Commande(
             rs.getLong("id_commande"),
              rs.getDate("date_commande"),
               rs.getString("etat"),
                rs.getLong("id_client"),
              rs.getLong("id_produit"),
               rs.getLong("id_commerce"),
               rs.getInt("quantite")
             );
                // Ajouter d'autres attributs de commande si nécessaire

                commandes.add(commande);
            }
            System.out.println("Commandes récupérées pour le client " + clientId + " : " + commandes.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public void modifierEtatCommande(Long idCommande, String etat) throws SQLException {
        String sql = "UPDATE commande SET etat = ? WHERE id_commande = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, etat);
            statement.setLong(2, idCommande);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Commande> getALLCommandes() throws SQLException {
        String SQL = "SELECT * FROM commande";
        List<Commande> commandes = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Commande commande = new Commande();
                commande.setId_commande(rs.getLong("id_commande"));
                commande.setid_client(rs.getLong("id_client"));
                commande.setId_produit(rs.getLong("id_produit"));
                commande.setQuantite(rs.getInt("quantite"));
                commande.setId_commerce(rs.getLong("id_commerce"));
                commande.setDate_commande(rs.getDate("date_commande"));
                commande.setEtat(rs.getString("etat"));
                commandes.add(commande);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des commandes : " + e.getMessage());
            throw e; // Propager l'exception pour la remonter à la servlet.
        }
        return commandes;
    }

    public List<Commande> getCommandesByCommerce(long id_commerce) throws SQLException {
        String SQL = "SELECT * FROM commande WHERE id_commerce = ?";
        List<Commande> commandes = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setLong(1, id_commerce);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Commande commande = new Commande();
                    commande.setId_commande(rs.getLong("id_commande"));
                    commande.setid_client(rs.getLong("id_client"));
                    commande.setId_produit(rs.getLong("id_produit"));
                    commande.setQuantite(rs.getInt("quantite"));
                    commande.setId_commerce(rs.getLong("id_commerce"));
                    commande.setDate_commande(rs.getDate("date_commande"));
                    commande.setEtat(rs.getString("etat"));
                    commandes.add(commande);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des commandes par id_commerce : " + e.getMessage());
            throw e;
        }
        return commandes;
    }

    
    public void updateCommandeEtat(long idCommande, String nouvelEtat) throws DAOException {
        String SQL = "UPDATE commande SET etat = ? WHERE id_commande = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {

            stmt.setString(1, nouvelEtat);
            stmt.setLong(2, idCommande);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de l'état de la commande", e);
        }
    }

    // Mapper les données SQL vers un objet Commande
    private Commande map(ResultSet resultSet) throws SQLException {
        return new Commande(
            resultSet.getLong("id_commande"),
            resultSet.getDate("date_commande"),
            resultSet.getString("etat"),
            resultSet.getLong("id_client"),
            resultSet.getLong("id_commerce"),
            resultSet.getLong("id_produit"),
            resultSet.getInt("quantite")  // Ajout de la quantité ici
        );
    }

	
}
