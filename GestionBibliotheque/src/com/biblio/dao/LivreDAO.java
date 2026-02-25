package com.biblio.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.biblio.config.DbConnection;
import com.biblio.model.Livre;

public class LivreDAO {

    public void ajouter(Livre l) {
        try {
            String sql = "INSERT INTO livres (titre, auteur, isbn, image_path, disponible) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql);
            ps.setString(1, l.getTitre());
            ps.setString(2, l.getAuteur());
            ps.setString(3, l.getIsbn());
            ps.setString(4, l.getImagePath());
            ps.setBoolean(5, true);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Livre> listerTout() {
        List<Livre> liste = new ArrayList<>();
        try {
            ResultSet rs = DbConnection.getConnection().createStatement().executeQuery("SELECT * FROM livres");
            while (rs.next()) {
                liste.add(new Livre(
                    rs.getInt("id"), rs.getString("titre"), 
                    rs.getString("auteur"), rs.getString("isbn"), 
                    rs.getBoolean("disponible"), rs.getString("image_path")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return liste;
    }

    public void supprimer(int id) {
        try {
            PreparedStatement ps = DbConnection.getConnection().prepareStatement("DELETE FROM livres WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public void modifier(Livre l) {
        try {
            String sql = "UPDATE livres SET titre=?, auteur=?, isbn=?, image_path=? WHERE id=?";
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, l.getTitre());
            ps.setString(2, l.getAuteur());
            ps.setString(3, l.getIsbn());
            ps.setString(4, l.getImagePath());
            ps.setInt(5, l.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}