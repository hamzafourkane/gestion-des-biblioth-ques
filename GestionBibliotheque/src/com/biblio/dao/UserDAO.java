package com.biblio.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.biblio.config.DbConnection;
import com.biblio.model.User;

public class UserDAO {


    public List<User> listerTout() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet rs = DbConnection.getConnection().createStatement().executeQuery("SELECT * FROM utilisateurs");
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("password"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }

    
    public void modifier(User u) {
        try {
            String sql = "UPDATE utilisateurs SET username=?, password=?, role=? WHERE id=?";
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword()); 
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

  
    public void ajouter(String username, String password, String role) throws SQLException {
        String sql = "INSERT INTO utilisateurs (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
        }
    }

 
    public void supprimer(int id) {
        try {
            PreparedStatement ps = DbConnection.getConnection().prepareStatement("DELETE FROM utilisateurs WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }


    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM utilisateurs WHERE username = ? AND password = ?";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}