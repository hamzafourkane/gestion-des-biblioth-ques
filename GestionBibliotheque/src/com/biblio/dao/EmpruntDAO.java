package com.biblio.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.biblio.config.DbConnection;
import com.biblio.model.Emprunt;

public class EmpruntDAO {


    public boolean emprunter(int userId, int livreId) {
        try {
            Connection conn = DbConnection.getConnection();


            PreparedStatement check = conn.prepareStatement("SELECT disponible FROM livres WHERE id = ?");
            check.setInt(1, livreId);
            ResultSet rs = check.executeQuery();
            if (rs.next() && !rs.getBoolean("disponible")) return false;


            String sql = "INSERT INTO emprunts (user_id, livre_id, date_emprunt, date_retour, retourne) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 15 DAY), FALSE)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, livreId);
            ps.executeUpdate();


            PreparedStatement update = conn.prepareStatement("UPDATE livres SET disponible = FALSE WHERE id = ?");
            update.setInt(1, livreId);
            update.executeUpdate();
            
            return true;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }


    public void retourner(int empruntId) {
        try {
            Connection conn = DbConnection.getConnection();


            PreparedStatement getLivre = conn.prepareStatement("SELECT livre_id FROM emprunts WHERE id = ?");
            getLivre.setInt(1, empruntId);
            ResultSet rs = getLivre.executeQuery();
            
            if (rs.next()) {
                int livreId = rs.getInt("livre_id");


                PreparedStatement ps = conn.prepareStatement("UPDATE emprunts SET retourne = TRUE WHERE id = ?");
                ps.setInt(1, empruntId);
                ps.executeUpdate();


                PreparedStatement update = conn.prepareStatement("UPDATE livres SET disponible = TRUE WHERE id = ?");
                update.setInt(1, livreId);
                update.executeUpdate();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }


 public List<Emprunt> getHistoriqueByUser(int userId) {
     List<Emprunt> liste = new ArrayList<>();
     try {
         String sql = "SELECT e.id, u.username, l.titre, e.date_emprunt, e.date_retour, e.retourne " +
                      "FROM emprunts e " +
                      "JOIN utilisateurs u ON e.user_id = u.id " +
                      "JOIN livres l ON e.livre_id = l.id " +
                      "WHERE e.user_id = ? ORDER BY e.date_emprunt DESC";
         Connection conn = DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setInt(1, userId);
         ResultSet rs = ps.executeQuery();
         while (rs.next()) {
             liste.add(new Emprunt(
                 rs.getInt("id"), rs.getString("username"), rs.getString("titre"),
                 rs.getDate("date_emprunt"), rs.getDate("date_retour"), rs.getBoolean("retourne")
             ));
         }
     } catch (SQLException e) { e.printStackTrace(); }
     return liste;
 }


 public void prolonger(int empruntId) {
     try {
         String sql = "UPDATE emprunts SET date_retour = DATE_ADD(date_retour, INTERVAL 15 DAY) WHERE id = ? AND retourne = FALSE";
         Connection conn = DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setInt(1, empruntId);
         ps.executeUpdate();
     } catch (SQLException e) { e.printStackTrace(); }
 }


    public List<Emprunt> listerEmpruntsEnCours() {
        List<Emprunt> liste = new ArrayList<>();
        try {
            String sql = "SELECT e.id, u.username, l.titre, e.date_emprunt, e.date_retour, e.retourne " +
                         "FROM emprunts e " +
                         "JOIN utilisateurs u ON e.user_id = u.id " +
                         "JOIN livres l ON e.livre_id = l.id " +
                         "WHERE e.retourne = FALSE";
            ResultSet rs = DbConnection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                liste.add(new Emprunt(
                    rs.getInt("id"), rs.getString("username"), rs.getString("titre"),
                    rs.getDate("date_emprunt"), rs.getDate("date_retour"), rs.getBoolean("retourne")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return liste;
    }
}