package com.biblio.dao;

import java.sql.*;
import com.biblio.config.DbConnection;

public class StatsDAO {
    
    public int getNombreLivres() {
        return getCount("SELECT COUNT(*) FROM livres");
    }

    public int getNombreUtilisateurs() {
        return getCount("SELECT COUNT(*) FROM utilisateurs");
    }

    public int getEmpruntsActifs() {
        return getCount("SELECT COUNT(*) FROM emprunts WHERE retourne = FALSE");
    }


    public String getLivrePopulaire() {
        String titre = "Aucun";
        try {
            String sql = "SELECT l.titre FROM emprunts e " +
                         "JOIN livres l ON e.livre_id = l.id " +
                         "GROUP BY l.id ORDER BY COUNT(*) DESC LIMIT 1";
            ResultSet rs = DbConnection.getConnection().createStatement().executeQuery(sql);
            if(rs.next()) titre = rs.getString(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return titre;
    }

    private int getCount(String sql) {
        try {
            ResultSet rs = DbConnection.getConnection().createStatement().executeQuery(sql);
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}