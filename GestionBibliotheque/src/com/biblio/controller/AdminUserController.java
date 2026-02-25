package com.biblio.controller;

import com.biblio.dao.UserDAO;
import com.biblio.model.User;
import com.biblio.view.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AdminUserController {
    private AdminUserView view;
    private AdminDashboardView dash;
    private UserDAO dao;

    public AdminUserController(AdminUserView view, AdminDashboardView dash) {
        this.view = view;
        this.dash = dash;
        this.dao = new UserDAO();
        chargerTable();


        view.table.getSelectionModel().addListSelectionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row != -1 && !e.getValueIsAdjusting()) {

                view.txtUsername.setText(view.model.getValueAt(row, 1).toString());
                view.txtPassword.setText(view.model.getValueAt(row, 2).toString()); 
                view.comboRole.setSelectedItem(view.model.getValueAt(row, 3).toString());
            }
        });


        view.btnAjouter.addActionListener(e -> {
            try {
                if(checkFields()) {
                    dao.ajouter(view.txtUsername.getText(), view.txtPassword.getText(), (String)view.comboRole.getSelectedItem());
                    chargerTable();
                    viderChamps();
                    JOptionPane.showMessageDialog(view, "Utilisateur ajouté !");
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage()); }
        });


        view.btnModifier.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row != -1) {
                if(checkFields()) {
                    int id = (int) view.model.getValueAt(row, 0); 


                    User u = new User(
                        id, 
                        view.txtUsername.getText(), 
                        view.txtPassword.getText(),
                        (String)view.comboRole.getSelectedItem()
                    );
                    
                    dao.modifier(u);
                    chargerTable();
                    viderChamps();
                    JOptionPane.showMessageDialog(view, "Utilisateur modifié avec succès !");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner une ligne à modifier.");
            }
        });

        
        view.btnSupprimer.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row != -1) {
                int confirm = JOptionPane.showConfirmDialog(view, "Supprimer cet utilisateur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION) {
                    dao.supprimer((int)view.model.getValueAt(row, 0));
                    chargerTable();
                    viderChamps();
                }
            }
        });


        view.btnRetour.addActionListener(e -> { view.dispose(); dash.setVisible(true); });
    }

    private void chargerTable() {
        view.model.setRowCount(0);
        List<User> list = dao.listerTout();
        for (User u : list) {

            view.model.addRow(new Object[]{u.getId(), u.getUsername(), u.getPassword(), u.getRole()});
        }
    }
    
    private void viderChamps() {
        view.txtUsername.setText("");
        view.txtPassword.setText("");
        view.table.clearSelection();
    }

    private boolean checkFields() {
        if(view.txtUsername.getText().isEmpty() || view.txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Tous les champs sont obligatoires.");
            return false;
        }
        return true;
    }
}